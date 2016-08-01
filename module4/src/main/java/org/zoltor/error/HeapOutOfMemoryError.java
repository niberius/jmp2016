/*
Run main method with the following JVM options;
-Xms15m -Xmx15m -Xmn9m -XX:SurvivorRatio=1

Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3332)
	at java.lang.String.concat(String.java:2032)
	at org.zoltor.error.HeapOutOfMemoryError$TmpClass.generateData(HeapOutOfMemoryError.java:43)
	at org.zoltor.error.HeapOutOfMemoryError$TmpClass.<init>(HeapOutOfMemoryError.java:29)
	at org.zoltor.error.HeapOutOfMemoryError.main(HeapOutOfMemoryError.java:53)
 */
package org.zoltor.error;

import java.lang.management.ManagementFactory;

/**
 * Created by zoltor on 01/08/2016.
 */
public class HeapOutOfMemoryError {

    static class TmpClass {

        private TmpClass linkedInstance;
        private String data = "";

        public TmpClass(TmpClass linkedInstance) {
            generateData();
            this.linkedInstance = linkedInstance;
        }

        public TmpClass() {
            this(null);
        }

        public String getData() {
            return data;
        }

        private void generateData() {
            for (int i = 0; i < 25000; i++) {
                data = data.concat(String.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("PID: " + ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        TmpClass tmpClass = new TmpClass();
        while (true) {
            TmpClass loopClass = new TmpClass(tmpClass);
            tmpClass = new TmpClass(loopClass);
        }
    }

}
