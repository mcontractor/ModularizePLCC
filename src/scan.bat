@ECHO off

IF NOT EXIST Java\Scan.class (
    ECHO Scan: no such file
    EXIT /B 1
)

java -cp EnvSource/Env.jar:./Java Scan
