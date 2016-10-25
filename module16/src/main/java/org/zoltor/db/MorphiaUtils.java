package org.zoltor.db;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import static org.zoltor.db.MongoConfig.*;

/**
 * Created by Pavel_Ardenka on 25/10/2016.
 */
public final class MorphiaUtils {

    private static Datastore datastore;

    private MorphiaUtils() {
        // Do not instantiate this class
    }

    public static Datastore getDataStore() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            morphia.mapPackage(ENTITIES_PACKAGE);
            datastore = morphia.createDatastore(new MongoClient(HOST, PORT), STORAGE_NAME);
            datastore.ensureIndexes();
        }
        return datastore;
    }
}
