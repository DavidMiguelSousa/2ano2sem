REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.app.user.console\target\base.app.user.console-0.1.0.jar;base.app.user.console\target\dependency\*;target\dependency\base.integrations.plugins.jar;

REM call the java VM, e.g, 
java -cp %BASE_CP% eapli.base.app.user.console.BaseUserApp
