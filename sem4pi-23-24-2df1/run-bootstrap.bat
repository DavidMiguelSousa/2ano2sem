REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.app.bootstrap\target\base.app.bootstrap-0.1.0.jar;base.app.bootstrap\target\dependency\*;target\dependency\base.integrations.plugins.jar;

REM call the java VM, e.g, 
java -cp %BASE_CP% eapli.base.app.bootstrap.BaseBootstrap
