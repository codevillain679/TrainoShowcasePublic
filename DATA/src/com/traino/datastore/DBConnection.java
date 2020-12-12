package com.traino.datastore;

import java.sql.*;

public class DBConnection {

    private Connection con;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection(){
        con = createConnection();
    }

    public static Connection createConnection()
    {
        Connection con = null;
        String url = System.getenv("url");
        String username = System.getenv("username");
        String password = System.getenv("password");
        try
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver"); //loading mysql driver
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }

    public ResultSet executeQuery(String sql){
        try{
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public void close(){
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException trouble) {
            trouble.printStackTrace();
        }
    }
}
