package Application;

import Persistance.DataProvider;
import Persistance.Goal;
import Persistance.Sporter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoalStore implements DataProvider<Goal> {
    @Override
    public Goal get(long id) {
        DBConnection dbcon = new DBConnection();

        ResultSet resultSet = dbcon.executeQuery("select * from goals where id in(" + id + ")");

        try {
            while (resultSet.next()) {
                Goal goal = new Goal(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("tag"), resultSet.getString("description"));
                dbcon.close();
                return goal;
            }
        } catch (SQLException trouble) {
            trouble.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goal> getAll() {
        return null;
    }

    @Override
    public void save(Goal goal) {

    }

    @Override
    public void update(Goal goal, String[] params) {

    }

    @Override
    public void delete(Goal goal) {

    }
}
