# Tervetulemast hieno otm prokkis
Tarkotuksena on tehrä tämmöne päkmäni peli

linkkii dokkeihikki löytyy [docs](https://github.com/haapseem/otm-harjoitustyo/tree/master/harjoitustyo/doc)

### build and run
mvn -T 8 clean install package && java -jar target/app-1.0-SNAPSHOT.jar

### This will do just everything
mvn -T 8 test && mvn -T 8 test jacoco:report && mvn -T 8 clean install package && java -jar target/app-1.0-SNAPSHOT.jar
