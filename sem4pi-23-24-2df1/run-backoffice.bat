REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.app.backoffice.console\target\base.app.backoffice.console-0.1.0.jar;base.app.backoffice.console\target\dependency\*;target\dependency\base.integrations.plugins.jar;

REM import interview models and job requirements
java -cp %BASE_CP% eapli.base.integrations.plugins.importers.ImportInterviewModelsAndJobRequirements

REM call the java VM, e.g,
java -cp %BASE_CP% eapli.base.app.backoffice.console.BaseBackoffice