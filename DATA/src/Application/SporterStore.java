package Application;

import Persistance.DataProvider;
import Persistance.Sporter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SporterStore implements DataProvider<Sporter> {

    @Override
    public Sporter get(long id) {
        DBConnection dbcon = new DBConnection();

        ResultSet resultSet = dbcon.executeQuery("select * from sporters where id in(" + id + ")");

        try {
            while (resultSet.next()) {
                Sporter sporter = new Sporter((int) id, resultSet.getString("name"),  resultSet.getString("surname"),  resultSet.getString("username"),  resultSet.getString("password"),  resultSet.getString("email"),  resultSet.getString("phone"),
                        resultSet.getBoolean("verified"),  resultSet.getDouble("weight"), resultSet.getDouble("length"), resultSet.getDouble("bmi"), resultSet.getDouble("fat"), resultSet.getString("bloodtype"));
                dbcon.close();
                return sporter;
            }
        } catch (SQLException trouble) {
            trouble.printStackTrace();
        }
        return null;
    }

        @Override
    public List<Sporter> getAll() {
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
}
