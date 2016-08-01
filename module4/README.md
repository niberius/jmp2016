Solution for the following tasks:
- java.lang.OutOfMemoryError: Java heap space. Create big objects continuously and make them stay in memory. Do not use arrays or collections.

-- org.zoltor.error.HeapOutOfMemoryError#main (but in any case everything goes down on Arrays#copyOf() inside String#concat() )

- java.lang.OutOfMemoryError: Metaspace. Load classes continuously and make them stay in memory.

-- org.zoltor.error.MetaOutOfMemoryError#main

- java.lang.StackOverflowError. Do not use recursive methods.

-- org.zoltor.error.StackOverflowError#main (solved with the recursion, but without recursive method. I don't know how to do it without the recursion completely :( )

Use the most appropriate JVM option for the early goal achievement. Heap structure should be: 3-Eden, 3-S0, 3-S1, 1m to thread stack.

Please note the JVM properties in the comments inside classes specified above.
