Module 17, WebServices

Task is:
```
Please, write Rest web services for User directory that provide CRUD operations on User.

User information should contain at least: first name, last name, login and email.

For creation of the user, please, use XML, for user update JSON format.

Create also Rest service to upload and download user logo as Image.

The application should be implemented and deploy on application server.

You can use any Rest implementation (Jersey, Restlet, etc.).

To test your application, please, use jersey Rest client that will cover all operations.

User data can be saved anywhere, database is not required.
```

Spring-MVC was used for implementation of the task (@RestController for service and RequestMappingHandlerAdapter bean with XML / JSON message converters).

The service: org.zoltor.rest.controller.UserController, the tests org.zoltor.UserControllerTest

To run the tests with embedded Tomcat:

1. Release port 8080
2. mvn clean tomcat7:run
3. mvn test

To run the tests on your own Tomcat:

1. Build WAR: mvn package -DskipTests
2. Deploy ./target/module17.war to your Tomcat
3. For default Tomcat instance address:port: mvn test
OR
4. For custom Tomcat instance address:port: mvn test -DtomcatAddress=localhost:8080
Where localhost - the host of your tomcat and 8080 - appropriate port