Simple car race :)

To run the race, run the main method in org.zoltor.RaceMain class.

Task is:
1. Create cars with different parameters. Start cars simultaneously and parallel. Observe which finishes first.

2. When the race is over - output the winner message 'Winner is XXXXX!'.

3. Disqualify one of cars from the race after 5 seconds by interrupting it's thread - car code needs to be modified for that.

public class Car implements Runnable {

        private static final long MAX_DISTANCE = 10000;

        Logger log = Logger.getLogger(getClass());

        private long friction;
        private long distance;

        private String name;

        public Car(String name, long friction) {
            this.name = name;
            this.friction = 100;
        }

        @Override
        public void run() {
            try {
                while (distance < MAX_DISTANCE) {
                    Thread.sleep(friction);
                    distance += 100;
                    log.info(name + " " + distance);
                }
            } catch (InterruptedException e) {
                log.error(e);
            }
        }

    }