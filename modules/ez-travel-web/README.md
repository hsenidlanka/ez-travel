## EZ-travel Web module

[![N|Solid](http://i.imgur.com/onfjH4w.jpg)](https://nodesource.com/products/nsolid)


### Introduction

EZ-travel web module is a spring boot web application which uses EZ-travel core server for services.

### Prerequisite
The project created using following sdk's and tools.
  - EZ-travel core server  
  - java version "1.8.0_74"
  - Java(TM) SE Runtime Environment (build 1.8.0_74-b02)
  - Apache Maven 3.3.9
  - Spring Boot 1.5.6.RELEASE
  - json-simple 1.1
  
**_and deployed and tested on_**
  - apache-tomcat-8.0.32 
  
### Configuration and building
EZ-travel web module uses  **EZ-travel core server** module. There for you need to build whole project first. 

First clone the Project from github
```sh
git clone https://github.com/hsenidlanka/ez-travel.git

```

Then go to build main pom.xml

```sh
mvn clean install
```
then go to core server pom and run it as spring boot project
```sh
mvn spring-boot:run
```
then go to ez-travel-web pom and run it as spring boot project
```sh
mvn spring-boot:run
```
