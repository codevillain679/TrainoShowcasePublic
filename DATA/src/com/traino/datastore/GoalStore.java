package com.traino.datastore;

import com.traino.app.Tag;
import com.traino.app.interfaces.DataProvider;
import com.traino.app.Goal;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoalStore implements DataProvider<Goal> {
    @Override
    public Goal get(long id) {
        DBConnection dbcon = new DBConnection();

        ResultSet resultSet = dbcon.executeQuery("select * from goals where id in(" + id + ")");

        try {
            while (resultSet.next()) {
                Goal goal = new Goal(resultSet.getString("title"),  resultSet.getString("description") , new Tag(resultSet.getString("tag")), new ArrayList<>());
                dbcon.close();
                return goal;
            }
        } catch (SQLException trouble) {
            trouble.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goal> getAll(String[] params) {
        //get goals from database;
        DBConnection dbcon = new DBConnection();
        String id = params[0];
        ResultSet resultSet = dbcon.executeQuery("select * from goals where id in(" + id + ")");

        List<Goal> goal_list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Goal goal = new Goal(resultSet.getString("title"), resultSet.getString("description"), new Tag(resultSet.getString("tag")), new ArrayList<>());
                goal_list.add(goal);
            }
        } catch (SQLException trouble) {
            trouble.printStackTrace();
        }
        dbcon.close();
        return goal_list;
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

    @Override
    public Goal get(String[] params) {
        return null;
    }
}
