import com.traino.app.*;
import com.traino.app.admin.SporterAdmin;
import com.traino.app.interfaces.Schedulable;
import com.traino.datastore.SporterStore;
import com.traino.view.ScheduleView;
import com.traino.view.SporterView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SporterStore dataStore = new SporterStore();

        SporterAdmin sporterAdmin = new SporterAdmin(dataStore);

        SporterView sporterView = new SporterView();
        ScheduleView scheduleView = new ScheduleView();

        Sporter sporter = null;

        int menuLoginOption = sporterView.selectLoginOption();

        switch (menuLoginOption) {
            case 0:
                //EXIT APPLICATION
                System.out.println("Application closed");
                System.exit(0);
                break;
            case 1: //REGISTER
                while (sporterAdmin.getLoggedInSporter() == null) {
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
                while (sporterAdmin.getLoggedInSporter() == null) {
                    sporter = sporterAdmin.login(sporterView.viewLogin());
                }

                //Show profile
                sporterView.viewProfile(sporter);
                break;
            default:
                System.out.println("Input invalid");
                System.exit(0);
                break;
        }

        int menuOption = sporterView.selectMenuOption();

        while (menuOption != -1) {
            switch (menuOption) {
                case 0:
                    //under development
                    break;
                case 1: //ADD GOAL
                    // Create goal
                    Goal goal = sporterView.addGoal();

                    // Add goal to database
                    sporterAdmin.addGoal(goal);
                    break;
                case 2: //EDIT GOAL
                    //Retrieve goals from database
                    List<Goal> allGoals = sporterAdmin.getAllGoals(sporter);

                    //Select goal
                    Goal edit_goal = sporterView.selectGoal(allGoals);

                    //Edit action
                    int goalEditMenuOption = sporterView.selectGoalMenuOption(edit_goal);

                    switch (goalEditMenuOption) {
                        case 1: //ADD EXERCISE
                            //Create exercise
                            Exercise exercise = sporterView.addExercise();

                            //Add exercise to goal exercise list
                            edit_goal.getAllExercises().add(exercise);

                            //Add exercise to database
                            sporterAdmin.addGoalExercise(edit_goal, exercise);
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }

                    //TODO: implement required methods for 'edit' in datastore
                    break;
                case 3: //ADD WORKOUT

                    break;
                case 4: //VIEW SCHEDULE (in excel)
                    List<Schedulable> schedule = new ArrayList<>();

                    List<Goal> allGoals4 = sporterAdmin.getAllGoals(sporter);

                    for(Goal goalitem : allGoals4){
                        schedule.addAll(goalitem.getScheduleItems());
                    }

                    scheduleView.showSchedule(schedule);

                    int scheduleOption = scheduleView.viewScheduleOptions();

                    switch(scheduleOption){
                        case 1:
                            //add workout
                            //Retrieve goals from database
                            List<Goal> allGoals2 = sporterAdmin.getAllGoals(sporter);

                            Goal selectedGoal = sporterView.selectGoal(allGoals2);

                            // Create workout + add exercise(s)
                            Workout workout = sporterView.addWorkout();

                            // add scheduleitem to selected goal
                            selectedGoal.addScheduleItem(workout);

                            // Add workout to database
                            sporterAdmin.addWorkout(workout, selectedGoal);

                            scheduleView.showSchedule(schedule);
                            break;
                        case 2:
                            //edit workout
                            break;
                        case 3:
                            //back to profile
                            break;
                    }

                    break;
                case 5: //GET SUGGESTIONS
                    List<Goal> allGoals3 = sporterAdmin.getAllGoals(sporter);

                    Goal selectedGoal2 = sporterView.selectGoal(allGoals3);

                    List<Workout> suggestions = sporterAdmin.getSuggestions(selectedGoal2);

                    Workout selectedWorkout = sporterView.selectWorkout(suggestions);

                    //  sporterAdmin.addWorkout(selectedWorkout);
                    break;
                case 6:
                    //EDIT PROFILE
                    Sporter sporter_edit = sporterView.editProfile(sporter);
                    sporterAdmin.updateSporter(sporter_edit);
                    sporterView.viewProfile(sporter_edit);
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9: //LOGOUT
                    //Ask for confirm
                    boolean confirm = sporterView.viewLogout();

                    //Clear sporter session
                    if (confirm) {
                        sporterAdmin.logout();
                        System.exit(0); //Close application
                    } else {
                        sporterView.viewProfile(sporter);
                    }
                    break;
            }
            menuOption = sporterView.selectMenuOption();
        }
    }
}
