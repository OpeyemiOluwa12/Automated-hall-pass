package com.opeyemi.automatedhallpass.bootstrap;

import java.io.File;

public class AppUtils {

    public static final String APPLICATION_DIRECTORY = System.getProperty("user.dir");
    public static final String ADMIN_LOGIN = APPLICATION_DIRECTORY+ File.separator + "adminlogin.txt";
    public static final String STUDENT_LOGIN = APPLICATION_DIRECTORY+ File.separator + "studentLogin.txt";
    public static final String ROLES = APPLICATION_DIRECTORY+ File.separator + "roles.txt";
}
