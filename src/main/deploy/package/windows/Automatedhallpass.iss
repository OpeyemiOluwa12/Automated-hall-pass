;This file will be executed next to the application bundle image
;I.e. current directory will contain folder Automatedhallpass1 with application files
[Setup]
AppId={{com.opeyemi.automatedhallpass.main}}
AppName=Automatedhallpass
AppVersion=1.0
AppVerName=Automatedhallpass 1.0
AppPublisher=Tindrah
AppComments=Automatedhallpass1
AppCopyright=Copyright (C) 2020
;AppPublisherURL=http://tindrah.com/
;AppSupportURL=http://tindrah.com/
;AppUpdatesURL=http://tindrah.com/
DefaultDirName={sd}\Automatedhallpass1
DisableStartupPrompt=Yes
DisableDirPage=Yes
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=No
DisableWelcomePage=No
DefaultGroupName=Tindrah
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1
OutputBaseFilename=Automatedhallpass1
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin
SetupIconFile=Automatedhallpass1\Automatedhallpass1.ico
UninstallDisplayIcon={app}\Automatedhallpass1.ico
UninstallDisplayName=Automatedhallpass1
WizardImageStretch=No
WizardSmallImageFile=Automatedhallpass1-setup-icon.bmp
ArchitecturesInstallIn64BitMode=


[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
Source: "Automatedhallpass1\Automatedhallpass1.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "Automatedhallpass1\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\Automatedhallpass1"; Filename: "{app}\Automatedhallpass1.exe"; IconFilename: "{app}\Automatedhallpass1.ico"; Check: returnFalse()
Name: "{commondesktop}\Automatedhallpass1"; Filename: "{app}\Automatedhallpass1.exe";  IconFilename: "{app}\Automatedhallpass1.ico"; Check: returnTrue()


[Run]
Filename: "{app}\Automatedhallpass1.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\Automatedhallpass1.exe"; Description: "{cm:LaunchProgram,Automatedhallpass1}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\Automatedhallpass1.exe"; Parameters: "-install -svcName ""Automatedhallpass1"" -svcDesc ""Automatedhallpass1"" -mainExe ""Automatedhallpass1.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\Automatedhallpass1.exe "; Parameters: "-uninstall -svcName Automatedhallpass1 -stopOnUninstall"; Check: returnFalse()

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;

function returnFalse(): Boolean;
begin
  Result := False;
end;

function InitializeSetup(): Boolean;
begin
// Possible future improvements:
//   if version less or same => just launch app
//   if upgrade => check if same app is running and wait for it to exit
//   Add pack200/unpack200 support?
  Result := True;
end;
