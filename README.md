# Tervetulemast hieno otm prokkis
Tarkotuksena on tehrä tämmöne päkmäni peli

linkkii dokkeihikki löytyy [docs](https://github.com/haapseem/otm-harjoitustyo/tree/master/harjoitustyo/doc)

### java
Open-jdk ei sisällä javaFX kirjastoa joten suosittelen oracle javaa

sudo add-apt-repository ppa:webupd8team/java

sudo apt update

sudo apt install oracle-java8-installer


### build and run
mvn -T 8 clean install package && java -jar target/app-1.0-SNAPSHOT.jar

### This will do just everything
mvn -T 8 test && mvn -T 8 test jacoco:report && mvn -T 8 clean install package && java -jar target/app-1.0-SNAPSHOT.jar
