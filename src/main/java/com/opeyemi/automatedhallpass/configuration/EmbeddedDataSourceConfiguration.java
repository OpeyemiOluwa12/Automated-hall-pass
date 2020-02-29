package com.opeyemi.automatedhallpass.configuration;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConfigurationProperties("spring.datasource")
public class EmbeddedDataSourceConfiguration {
    private final DataSourceProperties dataSourceProperties;
    @Value("${app.mariaDB4j.databaseName}")
    private String dbname;

    @Autowired
    private MariaDB4jSpringService mariaDB4jSpringService;

    public EmbeddedDataSourceConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public MariaDB4jSpringService mariaDB4jSpringService() {
        MariaDB4jSpringService mariadb4j = new MariaDB4jSpringService();
        mariadb4j.getConfiguration().addArg("--max_allowed_packet=1G");
        mariadb4j.getConfiguration().addArg("--innodb_file_per_table=true");
        mariadb4j.getConfiguration().addArg("--innodb_buffer_pool_size=128M");
        mariadb4j.getConfiguration().addArg("--default-time-zone=+01:00");
//        mariadb4j.getConfiguration().setSecurityDisabled(false);
        return mariadb4j;
    }

    @Bean
    public DataSource dataSource() {
        try {
            mariaDB4jSpringService.getDB().createDB(dbname,
                    dataSourceProperties.getUsername(),
                    dataSourceProperties.getPassword());
        } catch (ManagedProcessException e) {
            e.printStackTrace();
        }


        return DataSourceBuilder
                .create()
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .url(dataSourceProperties.getUrl())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.opeyemi.automatedhallpass");
        factory.setPersistenceUnitName("contentDB");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "none");
        jpaProperties.put("hibernate.show_sql", true);

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
        factory.setJpaProperties(jpaProperties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}