package com.account.dbaccess;

import com.account.selectors.Account;
import com.sun.jersey.spi.inject.Inject;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Pavithra on 11/7/14.
 */
public class HbaseDbaccess extends Account{
    static Logger log = Logger.getLogger(CassandraDbaccess.class.getName());
    private double CurrentBalance,UpdatedBalance;
    HTable table;
    Account account;


public HbaseDbaccess(HTable table){
this.table=table;
}

    @Override
    public boolean create() throws IOException {

        Put put = new Put(Bytes.toBytes("1"));

        put.add(Bytes.toBytes("Account"), Bytes.toBytes("Account_name"),Bytes.toBytes(account.getAc_name()));
        put.add(Bytes.toBytes("Account"), Bytes.toBytes("Account_no"),Bytes.toBytes(account.getAc_no()));
        put.add(Bytes.toBytes("Account"), Bytes.toBytes("Account_type"),Bytes.toBytes(account.getAc_type()));
        put.add(Bytes.toBytes("Account"), Bytes.toBytes("Contact_no"),Bytes.toBytes(account.getContact_no()));
        put.add(Bytes.toBytes("Account"), Bytes.toBytes("Email"),Bytes.toBytes(account.getEmail()));
        put.add(Bytes.toBytes("Account"), Bytes.toBytes("Balance"),Bytes.toBytes(account.getBalance()));

        table.put(put);
        return true;
    }

    @Override
    public Double addFunds( Double Deposit) throws IOException {
        Get get = new Get(Bytes.toBytes("row1"));

        get.addColumn(Bytes.toBytes("Account"), Bytes.toBytes("Balance"));

        Result result = table.get(get);

        byte[] val = result.getValue(Bytes.toBytes("Account"),
                Bytes.toBytes("Balance"));

        CurrentBalance = Double.parseDouble(Bytes.toString(val));
        UpdatedBalance = CurrentBalance + Deposit;
        if (UpdatedBalance <= 0) {
            log.error("ERROR Message:FAILED while updating adding funds into ColumnFamily!--ACCOUNT");
            return UpdatedBalance;
        } else {
            log.debug("DEBUG Message:Debugger for updating adding funds a account!");
            log.info("INFO Message: Successfully updating adding funds into the ColumnFamily--ACCOUNT");
            return CurrentBalance;
        }
    }


    @Override
    public Double withdrawFunds(Double Deposit) throws IOException {
        Get get = new Get(Bytes.toBytes("row1"));

        get.addColumn(Bytes.toBytes("Account"), Bytes.toBytes("Balance"));

        Result result = table.get(get);

        byte[] val = result.getValue(Bytes.toBytes("Account"),
                Bytes.toBytes("Balance"));

        CurrentBalance = Double.parseDouble(Bytes.toString(val));
        UpdatedBalance = CurrentBalance - Deposit;
        if (UpdatedBalance <= 0) {
            log.error("ERROR Message:FAILED while updating adding funds into ColumnFamily!--ACCOUNT");
            return UpdatedBalance;
        } else {
            log.debug("DEBUG Message:Debugger for updating adding funds a account!");
            log.info("INFO Message: Successfully updating adding funds into the ColumnFamily--ACCOUNT");
            return CurrentBalance;
        }

    }

    @Override
    public Double displayBalance() throws IOException {
        Get get = new Get(Bytes.toBytes("row1"));

        get.addColumn(Bytes.toBytes("Account"), Bytes.toBytes("Balance"));

        Result result = table.get(get);

        byte[] val = result.getValue(Bytes.toBytes("Account"),
                Bytes.toBytes("Balance"));

        CurrentBalance = Double.parseDouble(Bytes.toString(val));
        return CurrentBalance;
    }
}
