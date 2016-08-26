Implement java application to reproduce deadlock  below:
```
    |        |
    |        |
----○-------→○----
    ↑        |
    |        |
    |        |
    |        ↓
----○←-------○---
    |        |
    |        |
```

Collect thread dumps.

Results of the task:

- Java source code _(the main class is org.zoltor.Main)_

- Collected thread dumps _(ThreadDump.log file in the root of the project)_