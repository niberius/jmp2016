Homework task for Module 14 - Maven / Gradle.

Task:
```
Take any project from previous home tasks which contains more than one module (like DAO, Business layer, etc.). Rewrite the project using maven pom files individual for each modules and one pom final file for building entire project, run all tests and deploy it.
```

Based on homework for Module11. That project was splited on two modules:

- POJO
- Web

POM files was reworked (packaging for root - pom, for POJO - not specified (it means that jar will be used by default), for Web - war).

All "magic" described in root POM. It contains dependency management section and profiles fo running / deploying app.

To install dependencies to local repository, build all modules and run the app on embedded Tomcat7:
```
mvn clean install
```

After this, test app will be available on the address: http://localhost:8080/web

By command above the profile "embedded-tomcat-run" will be invoked implicitly.

To deploy the app on your own Tomcat, run the command:
```
mvn clean install -Pdeploy-tomcat7
```

Make sure, that you use Tomcat7+. Also, "deploy-tomcat7" contains several properties (e.g., Tomcat Host, Port, Credentials for deployment) which should be configured according to settings of your Tomcat. You can leave it as is or you can override some of them via CLI, e.g.:
```
mvn clean install -Pdeploy-tomcat7 -Dtomcat.host=tamcat-host-dev -Dtomcat.port=8081 -Dtomcat.username=admin -Dtomcat.password=h@xOr
```

The project does not contains any tests.