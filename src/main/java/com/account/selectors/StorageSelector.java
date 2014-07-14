package com.account.selectors;

import com.account.dbaccess.CassandraDbaccess;
import com.account.dbaccess.HbaseDbaccess;

/**
 * Created by Pavithra on 11/7/14.
 */
public class StorageSelector {

    public static Account dbChoice(Dbtype dbtype)
    {
        Account choice = null;

        switch (dbtype) {
            case CASSANDRA:
                choice = new CassandraDbaccess();
                break;

            case H_BASE:
                choice = new HbaseDbaccess();
                break;
            default:
                // throw some exception
                break;
        }
        return choice;
    }
}
