set HSQLDB_LIB=../hsqldb-2.4.0.jar

"%JAVA_HOME%\java.exe" -classpath %HSQLDB_LIB% org.hsqldb.Server -database.0 file:j2eeProject -port 9003

pause