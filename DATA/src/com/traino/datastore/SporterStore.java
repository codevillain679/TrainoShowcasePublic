package com.traino.datastore;

import com.traino.app.*;
import com.traino.app.interfaces.SporterProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SporterStore implements SporterProvider {

    //declare
    private DBConnection con;

    private Sporter authSporter;

    public SporterStore(){
        //init
        this.authSporter = null;

        //db connection
        this.con = new DBConnection();
    }

    @Override
    public List<Sporter> getAllSporters() {
        List<Sporter> allSporters = new ArrayList<>();
        ResultSet resultSet = con.executeQuery("SELECT * FROM SPORTERS ORDER BY ID");

        try{
            while(resultSet.next()){
                Sporter sporter = new Sporter(resultSet.getInt("id"), resultSet.getString("name"),  resultSet.getString("surname"),  resultSet.getString("username"),  resultSet.getString("password"),  resultSet.getString("email"),  resultSet.getString("phone"),
                        resultSet.getBoolean("verified"),  resultSet.getDouble("weight"), resultSet.getDouble("length"), resultSet.getDouble("fat"), resultSet.getString("bloodtype"));
                allSporters.add(sporter);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return allSporters;
    }

    @Override
    public void addSporter(Sporter sporter) {
        String stmt = "INSERT INTO SPORTERS(USERNAME, PASSWORD) VALUES("+sporter.getUsername()+", "+sporter.getPassword()+")";

        con.executeQuery(stmt);
    }

    public Sporter login(LoginBean loginBean) {
        Sporter sporter = null;

        //TODO login log entry maken in database

        ResultSet resultSet = con.executeQuery("select * from sporters where username in('" + loginBean.getUsername() + "') and password in('"+loginBean.getPassword()+"')");

        try {
            while (resultSet.next()) {
                sporter = new Sporter(resultSet.getInt("id"), resultSet.getString("name"),  resultSet.getString("surname"),  resultSet.getString("username"),  resultSet.getString("password"),  resultSet.getString("email"),  resultSet.getString("phone"),
                        resultSet.getBoolean("verified"),  resultSet.getDouble("weight"), resultSet.getDouble("length"), resultSet.getDouble("fat"), resultSet.getString("bloodtype"));
                this.authSporter = sporter;
            }
        } catch (SQLException trouble) {
            trouble.printStackTrace();
        }
        return sporter;
    }

    @Override
    public void logout() {
        //TODO logout log entry maken in database

        this.authSporter = null;
    }

    @Override
    public List<Goal> getAllGoals() {
        List<Goal> allGoals = new ArrayList<>();

        ResultSet resultSet = con.executeQuery("SELECT * FROM GOALS ORDER BY ID");

        try{
            while(resultSet.next()){
                //get goal from resultset
                Goal goal = new Goal(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description"), new Tag(resultSet.getString("tag")), new ArrayList<>());

                List<Exercise> goalExercises = new ArrayList<>();

                ResultSet resultSetExercises = con.executeQuery("SELECT * FROM EXERCISES WHERE GOAL_ID IN("+goal.getId()+") ORDER BY ID");

                try{
                    while(resultSetExercises.next()){
                        //get exercises for goal
                        Exercise exercise = new Exercise(resultSetExercises.getInt("id"), resultSetExercises.getString("name"), resultSetExercises.getInt("reps"), resultSetExercises.getInt("sets"), resultSetExercises.getString("symbol"));

                        //add exercise to goal exercise list
                        goalExercises.add(exercise);
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }

                //assign perceived goal exercise list to all exercises in goal
                goal.setAllExercises(goalExercises);

                //finally add goal to list of all goals
                allGoals.add(goal);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return allGoals;
    }

    @Override
    public void addGoal(Goal goal) {

    }

    @Override
    public void addWorkout(Workout workout) {

    }

    @Override
    public List<Workout> getAllWorkouts() {
        List<Workout> allWorkouts = new ArrayList<>();

        ResultSet resultSet = con.executeQuery("SELECT * FROM WORKOUTS ORDER BY ID");

        try{
            while(resultSet.next()){
                Workout workout = new Workout(resultSet.getInt("id"), resultSet.getString("activity"), Weekday.valueOf(resultSet.getString("day")), Status.valueOf(resultSet.getString("status")));
                allWorkouts.add(workout);

                //TODO what to do /w goal_id

            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return allWorkouts;
    }

    @Override
    public List<Exercise> getAllExercises(Workout workout) {
        List<Exercise> allExercises = new ArrayList<>();

        ResultSet resultSet = con.executeQuery("SELECT * FROM EXERCISES WHERE WORKOUT_ID IN("+workout.getId()+")");

        try{
            while(resultSet.next()){
                Exercise exercise = new Exercise(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("reps"), resultSet.getInt("sets"), resultSet.getString("symbol"));
                allExercises.add(exercise);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return allExercises;
    }

    @Override
    public void addGoalExercise(Goal goal, Exercise exercise) {

    }

    @Override
    public void addWorkoutExercise(Workout workout, Exercise exercise) {

    }

    @Override
    public List<Tag> getTags() {
        List<Tag> allTags = new ArrayList<>();

        ResultSet resultSet = con.executeQuery("SELECT * FROM TAGS ORDER BY ID");

        try{
            while(resultSet.next()){
                Tag tag = new Tag(resultSet.getString("link"));
                allTags.add(tag);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return allTags;
    }

    @Override
    public void addTag(Workout workout, Tag tag) {

    }

    @Override
    public List<Workout> getSuggestions(Goal goal) {
        return null;
    }

    @Override
    public Sporter getLoggedInSporter() {
        return authSporter;
    }

    public void setAuthSporter(Sporter sporter){
        this.authSporter = sporter;
    }

}
