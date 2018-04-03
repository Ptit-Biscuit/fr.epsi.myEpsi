set HSQLDB_LIB=../hsqldb-2.4.0.jar

"C:\Program Files\Java\jdk1.8.0_161\bin\java.exe" -classpath %HSQLDB_LIB% org.hsqldb.Server -database.0 file:j2eeProject -port 9003

pause