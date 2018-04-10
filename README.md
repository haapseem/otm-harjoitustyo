# Tervetulemast hieno otm prokkis
Tarkotuksena on tehrä tämmöne päkmäni peli

linkkii dokkeihikki löytyy [docs](https://github.com/haapseem/otm-harjoitustyo/tree/master/harjoitustyo/doc)

### java
Open-jdk ei sisällä javaFX kirjastoa joten suosittelen oracle javaa

```bash
whee@whoo:~$ sudo add-apt-repository ppa:webupd8team/java
whee@whoo:~$ sudo apt update
whee@whoo:~$ sudo apt install oracle-java8-installer
```

### build and run
```bash
whee@whoo:~$ mvn -T 8 clean install package && java -jar target/app-1.0-SNAPSHOT.jar
```

### This will do just everything
```bash
whee@whoo:~$ mvn -T 8 test && mvn -T 8 test jacoco:report && mvn -T 8 clean install package && java -jar target/app-1.0-SNAPSHOT.jar
```
