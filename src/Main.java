import com.traino.app.*;
import com.traino.app.admin.SporterAdmin;
import com.traino.app.interfaces.Schedulable;
import com.traino.datastore.SporterStore;
import com.traino.view.ScheduleView;
import com.traino.view.SporterView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static SporterStore dataStore;
    private static SporterAdmin sporterAdmin;
    private static SporterView sporterView;
    private static ScheduleView scheduleView;
    private static Sporter sporter;


    public static void main(String[] args) {
        dataStore = new SporterStore();

        sporterAdmin = new SporterAdmin(dataStore);

        sporterView = new SporterView();
        scheduleView = new ScheduleView();

        sporter = null;

        showMainMenu();

        showProfileMenu();
    }

    private static void showMainMenu(){
        int menuLoginOption = sporterView.selectLoginOption();

        switch (menuLoginOption) {
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
            case 3:
                //EXIT APPLICATION
                System.out.println("Application closed");
                System.exit(0);
                break;
        }
    }

    private static void showProfileMenu(){
        int menuOption = sporterView.selectMenuOption();

        while (menuOption != -1) {
            switch (menuOption) {
                case 0:
                    //under development
                    break;
                case 1:
                    //EDIT PROFILE
                    Sporter sporter_edit = sporterView.editProfile(sporter);
                    sporterAdmin.updateSporter(sporter_edit);
                    sporterView.viewProfile(sporter_edit);

                    break;
                case 2:
                    //ADD GOAL
                    Goal goal = sporterView.addGoal();

                    // Add goal to database
                    sporterAdmin.addGoal(goal);
                    break;
                case 3: //EDIT GOAL
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
                case 4: //VIEW SCHEDULE
                    List<Schedulable> schedule = new ArrayList<>();

                    List<Goal> allGoals4 = sporterAdmin.getAllGoals(sporter);

                    for(Goal goalitem : allGoals4){
                        schedule.addAll(goalitem.getScheduleItems());
                    }

                    scheduleView.showSchedule(schedule);

                    int scheduleOption = scheduleView.viewScheduleOptions();

                    switch(scheduleOption){
                        case 1: //ADD WORKOUT
                            //Retrieve goals from database
                            List<Goal> allGoals2 = sporterAdmin.getAllGoals(sporter);

                            Goal selectedGoal = sporterView.selectGoal(allGoals2);

                            //SHOW SUGGESTION workouts for this goal

                            List<Workout> suggestions = sporterAdmin.getSuggestions(selectedGoal);



                            // Create workout + add exercise(s)
                            // Workout workout = scheduleView.addWorkout();

                            // add scheduleitem to selected goal
                           // selectedGoal.addScheduleItem(workout);

                            // Add workout to database
                            //sporterAdmin.addWorkout(workout, selectedGoal);

                            scheduleView.showSchedule(schedule);
                            break;
                        case 2:
                            //edit workout


                            break;
                        case 3:
                            //Show Profile
                            sporterView.viewProfile(sporter);
                            break;
                    }

                    break;
                case 5: //LOGOUT
                    //Ask for confirm
                    boolean confirm = sporterView.viewLogout();

                    //Clear sporter session
                    if (confirm) {
                        sporterAdmin.logout();
                        showMainMenu();
                    } else {
                        sporterView.viewProfile(sporter);
                    }
                    break;
                case 9: //GET SUGGESTIONS
                    List<Goal> allGoals3 = sporterAdmin.getAllGoals(sporter);

                    Goal selectedGoal2 = sporterView.selectGoal(allGoals3);

                    List<Workout> suggestions = sporterAdmin.getSuggestions(selectedGoal2);

                    Workout selectedWorkout = sporterView.selectWorkout(suggestions);

                    //  sporterAdmin.addWorkout(selectedWorkout);
            }
            menuOption = sporterView.selectMenuOption();
        }
    }
}
