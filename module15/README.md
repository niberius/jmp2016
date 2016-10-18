The simple console app represents a notebook. All data stored in MongoDB.

There is ability to:

1. Create a note (with date, tag and text)
2. List all notes
3. Find the notes byt tag
4. Find the notes by text in note description or tag
5. Delete note (but you need to find it first - perform any operation in 2-4 point)

The main class is org.zoltor.Main

To build the project, run Maven command inside the project dir:

```
mvn clean compile assembly:single
```

After that, cd to target folder and type the command:

```
java -jar module15-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Wait while MongoDB binary will be downloaded and DataBase instance will be initilalized.
