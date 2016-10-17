package org.zoltor.db;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;

import java.io.IOException;

/**
 * Created by zoltor on 17/10/16.
 */
public class MongoServer {

    private MongodExecutable mongodExecutable;
    private MongodProcess mongodProcess;
    private static MongoServer instance;

    private MongoServer() {
        // Prevent from instantiating
    }

    public static MongoServer getInstance() {
        if (instance == null) {
            instance = new MongoServer();
            try {
                instance.init();
            } catch (IOException e) {
                throw new RuntimeException("Unable to start a database server. ", e);
            }
        }
        return instance;
    }

    public void stop() {
        mongodExecutable.stop();
        mongodProcess.stop();
    }

    public int getPort() {
        return mongodProcess.getConfig().net().getPort();
    }

    private void init() throws IOException {
        MongodStarter mongodStarter = MongodStarter.getDefaultInstance();
        IMongodConfig config = new MongodConfigBuilder()
                .version(Version.V3_3_1)
                .build();
        mongodExecutable = mongodStarter.prepare(config);
        mongodProcess = mongodExecutable.start();
    }


}
