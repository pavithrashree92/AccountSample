package com.account.connectors;


import org.apache.camel.component.hbase.HBaseHelper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;

import java.io.IOException;


/**
 * Created by Pavithra on 11/7/14.
 */
public class HbaseConnector  {
    HTable table;

    private static class LazyHolder {
        private static final HbaseConnector INSTANCE = new HbaseConnector();
    }

    public  HbaseConnector getInstance() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        this.table = new HTable(conf, "testtable");
        return LazyHolder.INSTANCE;
    }

}
