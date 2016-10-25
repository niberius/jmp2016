package org.zoltor.db;

/**
 * Created by zoltor on 18/10/16.
 */
public interface MongoConfig {

    // The host for MongoDB client
    String HOST = "localhost";

    // The port for MongoDB client
    int PORT = MongoServer.getInstance().getPort();

    // MongoDB storage
    String STORAGE_NAME = "default";

    // Package which contains POJO object for data mapping (for Morphia)
    String ENTITIES_PACKAGE = "org.zoltor.pojo";
}
