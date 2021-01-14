package com.traino.datastore;

import com.traino.app.*;
import com.traino.app.interfaces.Schedulable;
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
        String stmt = "INSERT INTO SPORTERS(USERNAME, PASSWORD, NAME, SURNAME, EMAIL, PHONE, VERIFIED, WEIGHT, LENGTH, BMI, FAT, BLOODTYPE) VALUES('"+sporter.getUsername()+"','"+sporter.getPassword()+"','"+sporter.getName()+"','"+sporter.getSurname()+"','"+sporter.getEmail()+"','"+sporter.getPhone()+"',"+false+","+sporter.getWeight()+","+sporter.getLength()+","+sporter.calculateBmi()+","+sporter.getFat()+",'"+sporter.getBloodtype()+"')";

        try{
            con.updateQuery(stmt);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSporter(Sporter sporter){
        String stmt = "UPDATE SPORTERS SET USERNAME='"+sporter.getUsername()+"',PASSWORD='"+sporter.getPassword()+"',NAME='"+sporter.getName()+"', SURNAME='"+sporter.getSurname()+"', EMAIL='"+sporter.getEmail()+"', PHONE='"+sporter.getPhone()+"', VERIFIED="+sporter.isVerified()+", WEIGHT="+sporter.getWeight()+", LENGTH="+sporter.getLength()+", BMI="+sporter.getBmi()+", FAT="+sporter.getFat()+", BLOODTYPE='"+sporter.getBloodtype()+"' WHERE ID IN("+sporter.getId()+")";

        try{
            con.updateQuery(stmt);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Sporter login(LoginBean loginBean) {
        Sporter sporter = null;

        //TODO login log entry maken in database

        ResultSet resultSet = con.executeQuery("select * from sporters where username in('" + loginBean.getUsername() + "') and password in('"+loginBean.getPassword()+"')");

        try {
            while (resultSet.next()) {
                sporter = new Sporter(resultSet.getInt("id"), resultSet.getString("name"),  resultSet.getString("surname"),  resultSet.getString("username"),  resultSet.getString("password"),  resultSet.getString("email"),  resultSet.getString("phone"),
                        resultSet.getBoolean("verified"),  resultSet.getDouble("weight"), resultSet.getDouble("length"), resultSet.getDouble("fat"), resultSet.getString("bloodtype"));

                sporter.setGoals(getAllGoals(sporter));

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
        ResultSet resultSet = con.executeQuery("select max(id)+1 as goal_id from goals");

        int id = 0;

        //get id for next goal in db from database

        try{
            while(resultSet.next()){
                id = resultSet.getInt("goal_id");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        String stmt = "INSERT INTO GOALS(SPORTER_ID, TITLE, TAG, DESCRIPTION) VALUES("+this.authSporter.getId()+",'"+goal.getTitle()+"','"+goal.getTag().getLink()+"','"+goal.getDescription()+"')";

        con.updateQuery(stmt);

        goal.setId(id);

        for(Exercise exercise : goal.getAllExercises()){
            addGoalExercise(goal, exercise);
        }
    }

    @Override
    public void addWorkout(Workout workout, Goal goal) {
        ResultSet resultSet = con.executeQuery("select max(id)+1 as workout_id from workouts");

        int id = 0;

        //get id for next workout in db from database
        try{
            while(resultSet.next()){
                id = resultSet.getInt("workout_id");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        String stmt = "INSERT INTO WORKOUTS(ACTIVITY, DAY, STATUS, GOAL_ID) VALUES('"+workout.getActivity()+"', '"+workout.getDay().toString()+"', '"+workout.getStatus()+"',"+goal.getId()+")";

        con.updateQuery(stmt);

        workout.setId(id);

        for(Exercise exercise : workout.getAllExercises()){
            addWorkoutExercise(workout, exercise);
        }
    }

    @Override
    public List<Workout> getAllWorkouts() {
        List<Workout> allWorkouts = new ArrayList<>();

        ResultSet resultSet = con.executeQuery("SELECT * FROM WORKOUTS ORDER BY ID");

        try{
            while(resultSet.next()){
                Workout workout = new Workout(resultSet.getInt("id"), resultSet.getString("activity"), Weekday.valueOf(resultSet.getString("day")), Status.valueOf(resultSet.getString("status")));
                allWorkouts.add(workout);

                //TODO what to do /w goal_id (remove?)

            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return allWorkouts;
    }

    @Override
    public List<Workout> getAllWorkouts(Sporter sporter) {
        List<Workout> allWorkouts = new ArrayList<>();

        ResultSet resultSet = con.executeQuery("SELECT * FROM WORKOUTS WHERE SPORTER_ID IN("+sporter.getId()+") ORDER BY ID");

        try{
            while(resultSet.next()){
                Workout workout = new Workout(resultSet.getInt("id"), resultSet.getString("activity"), Weekday.valueOf(resultSet.getString("day")), Status.valueOf(resultSet.getString("status")));
                allWorkouts.add(workout);

                workout.setAllExercises(getAllExercises(workout));

                //TODO what to do /w goal_id (remove?)

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
        String stmt = "INSERT INTO EXERCISES(NAME,REPS,SETS,SYMBOL,GOAL_ID) VALUES('"+exercise.getName()+"', "+exercise.getReps()+","+exercise.getSets()+",'"+exercise.getSymbol()+"', "+goal.getId()+")";

        con.updateQuery(stmt);
    }

    @Override
    public void addWorkoutExercise(Workout workout, Exercise exercise) {
        String stmt = "INSERT INTO EXERCISES(NAME,REPS,SETS,SYMBOL,WORKOUT_ID) VALUES('"+exercise.getName()+"', "+exercise.getReps()+","+exercise.getSets()+",'"+exercise.getSymbol()+"', "+workout.getId()+")";

        con.updateQuery(stmt);
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
    public Sporter getLoggedInSporter() {
        return authSporter;
    }

    @Override
    public List<Goal> getAllGoals(Sporter sporter) {
        List<Goal> allGoals = new ArrayList<>();

        ResultSet resultSet = con.executeQuery("SELECT * FROM GOALS WHERE SPORTER_ID IN("+sporter.getId()+") ORDER BY ID");

        try{
            while(resultSet.next()){
                //get goal from resultset
                Goal goal = new Goal(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description"), new Tag(resultSet.getString("tag")), new ArrayList<>());

                //get goal exercises
                List<Exercise> goalExercises = getAllExercises(goal);

                //assign perceived goal exercise list to all exercises in goal
                goal.setAllExercises(goalExercises);

                //get goal schedule items
                List<Workout> scheduleItems = getAllWorkouts(goal);


                //assign schedule items to goal
                for(Workout w : scheduleItems){
                    Schedulable s = w;
                    goal.addScheduleItem(s);
                }


                // assign goal to list of all goals
                allGoals.add(goal);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return allGoals;
    }

    @Override
    public List<Exercise> getAllExercises(Goal goal) {
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

        return goalExercises;
    }

    @Override
    public List<Workout> getAllWorkouts(Goal goal){

        List<Workout> allWorkouts = new ArrayList<>();

        String stmt = "SELECT * FROM WORKOUTS WHERE GOAL_ID IN("+goal.getId()+")";

        ResultSet resultSet = con.executeQuery(stmt);

        try{
            while(resultSet.next()){
                Workout workout = new Workout(resultSet.getInt("id"), resultSet.getString("activity"), Weekday.valueOf(resultSet.getString("day")), Status.valueOf(resultSet.getString("status")));

                List<Exercise> workout_exercises = getAllExercises(workout);

                workout.setAllExercises(workout_exercises);

                allWorkouts.add(workout);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return allWorkouts;

    }
}
