package persistence.dao.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.Properties;

public class Connexion {

    private static Statement stm;
    private static Connection connection;
    
    static{
        try {
                Class.forName(Properties.SQL_DRIVER);
                connection = DriverManager.getConnection(Properties.DATABASE,Properties.DATABASE_USER,Properties.DATABASE_PASSWORD);
                stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static ResultSet select(String cmd) throws Exception{
        stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        return stm.executeQuery(cmd);
    }

    public static int update(String cmd){
        try {
            stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stm.executeUpdate(cmd);
            return stm.getUpdateCount();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return -1;
    }
}
