package com.traino.datastore;

import com.traino.app.interfaces.DataProvider;
import com.traino.app.LoginBean;
import com.traino.app.Sporter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SporterStore implements DataProvider<Sporter> {

    Sporter sporter;

    @Override
    public Sporter get(long id) {
        DBConnection dbcon = new DBConnection();

        ResultSet resultSet = dbcon.executeQuery("select * from sporters where id in(" + id + ")");

        try {
            while (resultSet.next()) {
                Sporter sporter = new Sporter((int) id, resultSet.getString("name"),  resultSet.getString("surname"),  resultSet.getString("username"),  resultSet.getString("password"),  resultSet.getString("email"),  resultSet.getString("phone"),
                        resultSet.getBoolean("verified"),  resultSet.getDouble("weight"), resultSet.getDouble("length"), resultSet.getDouble("fat"), resultSet.getString("bloodtype"));
                dbcon.close();
                this.sporter = sporter;
            }
        } catch (SQLException trouble) {
            trouble.printStackTrace();
        }
        return null;
    }

        @Override
    public List<Sporter> getAll(String[] params) {
        return null;
    }

    @Override
    public void save(Sporter sporter) {

    }

    @Override
    public void update(Sporter sporter, String[] params) {

    }

    @Override
    public void delete(Sporter sporter) {

    }

    @Override
    public Sporter get(String[] params) {
        //used for login
        LoginBean loginBean = new LoginBean(params[0], params[1]);
        login(loginBean);
        return this.sporter;
    }

    public boolean login(LoginBean loginBean) {
        DBConnection dbcon = new DBConnection();

        ResultSet resultSet = dbcon.executeQuery("select * from sporters where username in('" + loginBean.getUsername() + "') and password in('"+loginBean.getPassword()+"')");

        try {
            while (resultSet.next()) {
                Sporter sporter = new Sporter(resultSet.getInt("id"), resultSet.getString("name"),  resultSet.getString("surname"),  resultSet.getString("username"),  resultSet.getString("password"),  resultSet.getString("email"),  resultSet.getString("phone"),
                        resultSet.getBoolean("verified"),  resultSet.getDouble("weight"), resultSet.getDouble("length"), resultSet.getDouble("fat"), resultSet.getString("bloodtype"));
                this.sporter = sporter;
                return true;
            }
        } catch (SQLException trouble) {
            trouble.printStackTrace();
        }
        return false;
    }
}
