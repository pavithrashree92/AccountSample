package com.account.dbaccess;

import com.account.selectors.Account;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;
import org.apache.log4j.Logger;

/**
 * Created by Pavithra on 11/7/14.
 */
public class CassandraDbaccess extends Account {
    private static StringSerializer stringSerializer = StringSerializer.get();

    static Logger log = Logger.getLogger(CassandraDbaccess.class.getName());
    private Double CurrentBalance,UpdatedBalance;
    String ksp;
    String column_family;
    Cluster cluster;
Account account;

public CassandraDbaccess(String keySpace,String Column_family,Cluster cluster){
    this.ksp= keySpace;
    this.column_family=Column_family;
    this.cluster=cluster;

}



    @Override
    public boolean create() {
        Keyspace keyspace = HFactory.createKeyspace(ksp, cluster);
        Mutator<String> mutator = HFactory.createMutator(keyspace, StringSerializer.get());
        //inserting into Column Family
        mutator.insert(account.getAc_no(), column_family, HFactory.createStringColumn("AccountHolder_name", account.getAc_name()));
        mutator.insert(account.getAc_no(), column_family, HFactory.createStringColumn("Type of account", account.getAc_type()));
        mutator.insert(account.getAc_no(), column_family, HFactory.createStringColumn("Contact_number", account.getContact_no()));
        mutator.insert(account.getAc_no(), column_family, HFactory.createStringColumn("Email", account.getEmail()));
        mutator.insert(account.getAc_no(), column_family, HFactory.createStringColumn("Balance", String.valueOf(account.getBalance())));

        if (UpdatedBalance<=0){
            log.error("ERROR Message:FAILED while updating adding funds into ColumnFamily!--ACCOUNT");
            return true;
        }
        else{
            log.debug("DEBUG Message:Debugger for creating a account!");
            log.info("INFO Message: Successfully inserted into the ColumnFamily");
            return false;
        }
    //log messages


    }

    @Override
    public Double addFunds(Double Deposit) {
        Keyspace keyspace = HFactory.createKeyspace(ksp, cluster);
        ColumnQuery<String, String, String> columnQuery =  HFactory.createStringColumnQuery(keyspace);
        columnQuery.setColumnFamily(column_family).setKey(account.getAc_no()).setName(String.valueOf(account.getBalance()));
        QueryResult<HColumn<String, String>> result = columnQuery.execute();

        //adding funds to the available balance
        CurrentBalance=Double.parseDouble(result.get().getValue());
        UpdatedBalance=CurrentBalance+Deposit;


        Mutator<String> mutator = HFactory.createMutator(keyspace, StringSerializer.get());
        mutator.insert(account.getAc_no(), column_family, HFactory.createStringColumn("Balance", String.valueOf(account.getBalance())));

    //log messages
        log.debug("DEBUG Message:Debugger for updating adding funds a account!");
        log.error("ERROR Message:FAILED while updating adding funds into ColumnFamily!--ACCOUNT");
        log.info("INFO Message: Successfully updating adding funds into the ColumnFamily--ACCOUNT");
return UpdatedBalance;

    }

    @Override
    public Double withdrawFunds(Double Deposit) {
        Keyspace keyspace = HFactory.createKeyspace(ksp, cluster);
        ColumnQuery<String, String, String> columnQuery =  HFactory.createStringColumnQuery(keyspace);
        columnQuery.setColumnFamily(column_family).setKey(account.getAc_no()).setName(String.valueOf(account.getBalance()));
        QueryResult<HColumn<String, String>> result = columnQuery.execute();

        //adding funds to the available balance
        CurrentBalance=Double.parseDouble(result.get().getValue());
        UpdatedBalance=CurrentBalance-Deposit;

        if (UpdatedBalance<=0){
            log.error("ERROR Message:FAILED while updating adding funds into ColumnFamily!--ACCOUNT");
            return UpdatedBalance;
        }
    else {
            Mutator<String> mutator = HFactory.createMutator(keyspace, StringSerializer.get());
            mutator.insert(account.getAc_no(), column_family, HFactory.createStringColumn("Balance", String.valueOf(account.getBalance())));

            //log messages
            log.debug("DEBUG Message:Debugger for updating adding funds a account!");
            log.info("INFO Message: Successfully updating adding funds into the ColumnFamily--ACCOUNT");
            return CurrentBalance;
        }
    }


    public Double displayBalance(){
        Keyspace keyspace = HFactory.createKeyspace(ksp, cluster);
        ColumnQuery<String, String, String> columnQuery =  HFactory.createStringColumnQuery(keyspace);
        columnQuery.setColumnFamily(column_family).setKey(account.getAc_no()).setName(String.valueOf(account.getBalance()));
        QueryResult<HColumn<String, String>> result = columnQuery.execute();
        CurrentBalance=Double.parseDouble(result.get().getValue());
        log.debug("DEBUG Message:Debugger for Displaying balance from the account!");
        log.error("ERROR Message:FAILED while Retrieving from ColumnFamily!--ACCOUNT");
        log.info("INFO Message: Successfully Retrieved from the ColumnFamily--ACCOUNT");
        return CurrentBalance;

    //log messages

    }
}
