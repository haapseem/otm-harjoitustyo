# Pacman
Tarkotuksena on tehrä tämmöne päkmäni peli

Peliä voi pelata w,a,s,d tai nuolinäppäimillä

linkkii dokkeihikki löytyy [docs](https://github.com/haapseem/otm-harjoitustyo/tree/master/harjoitustyo/doc)

Määritelmä: [määritelmä](https://github.com/haapseem/otm-harjoitustyo/tree/master/harjoitustyo/doc/hahmotelma.md)

### java
Open-jdk ei sisällä javaFX kirjastoa joten suosittelen oracle javaa

```bash
whee@whoo:~$ sudo add-apt-repository ppa:webupd8team/java
whee@whoo:~$ sudo apt update
whee@whoo:~$ sudo apt install oracle-java8-installer
```

### build and run
```bash
whee@whoo:~$ mvn clean package && java -jar target/app-1.0-SNAPSHOT.jar
whee@whoo:~$ # OR
whee@whoo:~$ mvn clean exec:java -Dexec.mainClass="com.otm.App"
```

### Test
```bash
whee@whoo:~$ mvn test
whee@whoo:~$ mvn test jacoco:report
```

### Luokkakaavio
![luokkakaavio](https://github.com/haapseem/otm-harjoitustyo/blob/master/harjoitustyo/doc/luokkaKaavio.png?raw=true)
