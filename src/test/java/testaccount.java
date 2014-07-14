import com.account.dbaccess.CassandraDbaccess;
import me.prettyprint.hector.api.Cluster;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Pavithra on 14/7/14.
 */
public class testaccount {

    private CassandraDbaccess cassandraaccount;
    boolean done=true;
    Cluster cluster;

    @Before
    public void setUp(){
        cassandraaccount=new CassandraDbaccess("","",cluster);
    }

    @Test
    public void testcreate(){

        Assert.assertEquals(cassandraaccount.create(),done);

    }

    @Test
    public void testaddfunds(){

        Assert.assertEquals(cassandraaccount.addFunds(100.0).toString(),200.0);

    }

}
