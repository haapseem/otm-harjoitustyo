:a
bash -c "mvn -T 8 -DskipTests package"

java -jar target/app-1.0-SNAPSHOT.jar
goto a