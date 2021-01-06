import com.traino.app.*;
import com.traino.app.admin.ScheduleAdmin;
import com.traino.app.admin.SporterAdmin;
import com.traino.datastoredemo.ScheduleStoreDemo;
import com.traino.datastore.SporterStore;
import com.traino.view.SporterView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SporterStore dataStore = new SporterStore();
        SporterAdmin sporterAdmin = new SporterAdmin(dataStore);
        SporterView sporterView = new SporterView();

       //TODO: add ScheduleStore() instead of ScheduleStoreDemo()
        ScheduleAdmin scheduleAdmin = new ScheduleAdmin(new ScheduleStoreDemo());

        Sporter sporter = null;

        int menuLoginOption = sporterView.selectLoginOption();

        switch(menuLoginOption){
            case 1: //REGISTER
                while(sporterAdmin.getLoggedInSporter() == null){
                    Sporter newSporter = sporterView.viewRegister();

                    //Upload new user to database
                    sporterAdmin.addSporter(newSporter);

                    //Login
                    newSporter = sporterAdmin.login(new LoginBean(newSporter.getUsername(), newSporter.getPassword()));

                    //Show profile
                    sporterView.viewProfile(newSporter);
                }
                break;
            case 2: //LOGIN
                while(sporterAdmin.getLoggedInSporter() == null){
                    sporter = sporterAdmin.login(sporterView.viewLogin());
                }

                //Show profile
                sporterView.viewProfile(sporter);
                break;
            default: break;
        }

        int menuOption = sporterView.selectMenuOption();

        switch(menuOption){
            case 0: break;
            case 1: //ADD GOAL
                // Create goal
                Goal goal = sporterView.addGoal();

                // Add goal to database
                sporterAdmin.addGoal(goal);
                break;
            case 2: //EDIT GOAL
                //Retrieve goals from database
                sporter.setGoals(sporterAdmin.getAllGoals(sporter));

                //Select goal
                Goal edit_goal = sporterView.selectGoal(sporter);

                //Edit action
                int goalEditMenuOption = sporterView.selectGoalMenuOption(edit_goal);

                switch(goalEditMenuOption){
                    case 1: //ADD EXERCISE
                        //Create exercise
                        Exercise exercise = sporterView.addExercise();

                        //Add exercise to goal exercise list
                        edit_goal.getAllExercises().add(exercise);

                        //Add exercise to database
                        sporterAdmin.addGoalExercise(edit_goal, exercise);
                        break;
                    default:
                        break;
                }

                //TODO: implement required methods for 'edit' in datastore
                break;
            case 3: //ADD WORKOUT
                //Retrieve goals from database
                List<Goal> allGoals = sporterAdmin.getAllGoals(sporter);

                // Create workout + add exercise(s)
                Workout workout = sporterView.addWorkout();

                // Add workout to database
                sporterAdmin.addWorkout(workout);
                break;
            case 4: //VIEW SCHEDULE (in excel)
                //TODO: Update ScheduleStoreDemo to ScheduleStore + rewrite excel export
                break;
            case 5: //GET SUGGESTIONS
                Goal selectedGoal = sporterView.selectGoal(sporter);

                List<Workout> suggestions = sporterAdmin.getSuggestions(selectedGoal);

                Workout selectedWorkout = sporterView.selectWorkout(suggestions);

              //  sporterAdmin.addWorkout(selectedWorkout);
                break;
            case 6: break;
            case 7: break;
            case 8: break;
            case 9: //LOGOUT
                //Ask for confirm
                boolean confirm = sporterView.viewLogout();

                //Clear sporter session
                if(confirm){
                    sporterAdmin.logout();
                    sporterAdmin.login(sporterView.viewLogin());
                }else{
                    sporterView.viewProfile(sporter);
                }
                break;
        }
    }
}
