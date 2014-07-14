package com.account.connectors;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.ddl.ColumnFamilyDefinition;
import me.prettyprint.hector.api.ddl.ComparatorType;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.factory.HFactory;

/**
 * Created by Pavithra on 11/7/14.
 */
public class CassandraConnector  {
    Cluster cluster;
    private static class LazyHolder {
        private static final CassandraConnector INSTANCE = new CassandraConnector();

    }

    public CassandraConnector getInstance(String keySpace, String columnFamily, Cluster cluster) {
       this.cluster = HFactory.getOrCreateCluster("Test-Cluster", "localhost:9160");
     /*   String keySpace="Bank";
        String columnFamily="Account";*/
        KeyspaceDefinition ksDef = HFactory.createKeyspaceDefinition(keySpace);
        cluster.addKeyspace(ksDef);

        ColumnFamilyDefinition cfDef =HFactory.createColumnFamilyDefinition(keySpace, columnFamily, ComparatorType.UTF8TYPE);
        cluster.addColumnFamily(cfDef);

        return LazyHolder.INSTANCE;
    }
}
