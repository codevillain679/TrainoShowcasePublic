import com.traino.app.*;
import com.traino.app.admin.ScheduleAdmin;
import com.traino.app.admin.SporterAdmin;
import datastoredemo.ScheduleStoreDemo;
import datastoredemo.SporterStoreDemo;
import viewdemo.ScheduleViewDemo;
import viewdemo.SporterViewDemo;

import java.io.IOException;

public class MainDemo {
    public static void main(String[] args) {

        //TODO: Add comments explain what's going on

        SporterAdmin sporterAdmin = new SporterAdmin(new SporterStoreDemo());
        ScheduleAdmin scheduleAdmin = new ScheduleAdmin(new ScheduleStoreDemo());

        SporterViewDemo sporterViewDemo = new SporterViewDemo();
        ScheduleViewDemo scheduleViewDemo = new ScheduleViewDemo();

        LoginBean loginBean = sporterViewDemo.showLogin();
        Sporter sporter = sporterAdmin.login(loginBean);
        sporterViewDemo.showProfile(sporter);
        int menuChoice = sporterViewDemo.showMenu();

        int logout = 9;

        while(menuChoice != logout) {
            switch(menuChoice){
                case 1: //Add goal
                    Goal goal = sporterViewDemo.addGoal();
                    sporterAdmin.addGoal(goal);
                    sporterViewDemo.showGoal(goal);
                    break;
                case 2: //View goal
                    Goal goal2 = sporterViewDemo.selectGoal(sporterAdmin.getAllGoals());
                    Exercise exercise2 = sporterViewDemo.editGoal(goal2);
                    sporterAdmin.addGoalExercise(goal2, exercise2);
                    break;
                case 3: //Add workout
//                    Goal goal3 = sporterViewDemo.selectGoal(sporterAdmin.getAllGoals());
//                    sporterViewDemo.showGoal(goal3);
//                    //List<Workout> suggestions = sporterAdmin.getSuggestions(goal3);
//                    //Workout suggested = sporterViewDemo.showSuggestions(suggestions);
//                    if(suggested != null){
//                        //sporterAdmin.addWorkout(goal3, suggested);
//                        scheduleAdmin.addScheduleItem(suggested);
//                    }else {
//                        Workout workout = sporterViewDemo.addWorkout();
//                        //sporterAdmin.addWorkout(goal3, suggested);
//                        scheduleAdmin.addScheduleItem(workout);
//                    }
                    //display schedule
                    try {
                        scheduleViewDemo.showSchedule(scheduleAdmin.getAllScheduleItems());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4: //Show Schedule
                    try {
                        scheduleViewDemo.showSchedule(scheduleAdmin.getAllScheduleItems());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 9: // Log out
                    sporterAdmin.logout();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + menuChoice);
            }

            sporterViewDemo.showProfile(sporter);
            menuChoice = sporterViewDemo.showMenu();
        }

    }
}
