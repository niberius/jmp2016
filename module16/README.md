Module 16 - Unit-Tests

Task is following:
```
Use your existing application and cover functionality by unit tests - dao and services.

1. Implement JUnit test and cover all service API methods

2. Implement mock object for DAO using any mocking framework
```

Dummy app has no services. So there are no tests for point 1.

Tests for Dao object are here: ./src/test/java/NotesDaoImplTest.java

Please note that when tests are starting, then MongoServer class initializes embedded MongoDB server and you have to wait until MongoDB binary downloading will be finished :( But the tests not interact with Database server (everything is mocked).

