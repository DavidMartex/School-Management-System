package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbconnect;

/**
 * Created by Martex on 6/9/2017.
 */
public class LoginModel {

Connection connection;

public LoginModel(){
try
{
    this.connection = dbconnect.getConnection();
}
catch (SQLException ex)
    {
        ex.printStackTrace();
    }

    if(this.connection==null)
    {
        System.exit(1);
        }

    }

    public boolean isDatabaseConnected(){
    return this.connection != null;
    }

    public boolean isLogin(String user, String pass) throws Exception{
        PreparedStatement pr= null;
        ResultSet rs = null;

        String sql = "SELECT * FROM login where username = ? and password = ?";

        try{
            pr=this.connection.prepareStatement(sql);
            pr.setString(1,user);
            pr.setString(2,pass);


            rs=pr.executeQuery();


            boolean bool1;

            return rs.next();

        } catch(Exception ex) {

            ex.printStackTrace();

            rs.close();
             pr.close();
        }
        return true;

    }
}
