REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=follow.up.server\target\follow.up.server-0.1.0.jar;follow.up.server\target\dependency\*;follow.up.server\target\classes\*;

REM call the java VM, e.g,
java -Denvironment=production -cp %BASE_CP% serverInit/Server