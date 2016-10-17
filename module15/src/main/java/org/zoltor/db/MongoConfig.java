package org.zoltor.db;

/**
 * Created by zoltor on 18/10/16.
 */
public interface MongoConfig {

    String HOST = "localhost";
    int PORT = MongoServer.getInstance().getPort();
    String STORAGE_NAME = "default";
    String ENTITIES_PACKAGE = "org.zoltor.pojo";
}
