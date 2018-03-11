set HSQLDB_LIB=../hsqldb-2.4.0.jar

echo off
cls
echo "----------- Etapes a suivre -----------"
echo "etape 1/3 : type => HSQL Database Engine Server"
echo "etape 2/3 : URL => jdbc:hsqldb:hsql://localhost:9003/"
echo "etape 3/3 : Appuyer sur connexion"

"C:\Program Files\Java\jdk1.8.0_131\bin\java.exe" -classpath %HSQLDB_LIB% org.hsqldb.util.DatabaseManager