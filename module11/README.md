Simple web-app with Spring MVC usage.

The app simulates booking the tickets to cinema. Run Maven project with command:
```
mvn tomcat7:run
```

Then navigate in browser to http://localhost:8080/module11/

Key features:

- spring context is using
- annotation-based configuration
- list of available seances
- list of available places
- book place
- the different bean scopes
- all info stored in memory while app is living

Current Module highlights:

- support some template solution: Tiles or Thymeleaf
- handle exceptions with help of Spring solutions

What was not done:

- try to use themas, prepare 2 themes
- introduce i18n: support RU and EN languages _I can't get it works. Confused with Thymeleaf and Spring message resolvers_
- CRUD operation for all entities (persistence is optional, you could use in memory db or just session, it is up to you). Search, sort, pagination is optional. _No CRUD operations. All changes done via operations with the beans_
- app should have authorization: you can implement security via interceptors or use Spring Security (this variant for highest mark)