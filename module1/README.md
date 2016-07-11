Just a simple project consist of four modules:
- utils - common util classes
- communicator - http client and text processors (currently - JSON only)
- provider - classes for http://jsonplaceholder.typicode.com (currently - users only)
- application - main module which allow user to interact with application.

To run the application:
0. Build it using the bash-script build.sh (and skip steps 1-2)

1. Build it and install to the local repo:
cd /path/to/module1 && mvn clean install

2. Build application standalone jar:
cd /path/to/module1/application && mvn compile assembly:single

3. Run it:
cd /path/to/module1/application/target && java -jar application-1.0-SNAPSHOT-jar-with-dependencies.jar