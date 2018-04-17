:a
bash -c "mvn -DskipTests package"
java -jar target/app-1.0-SNAPSHOT.jar
goto a