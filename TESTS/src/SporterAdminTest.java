import com.traino.app.*;
import com.traino.app.admin.SporterAdmin;
import com.traino.datastoredemo.SporterStoreDemo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SporterAdminTest {

    SporterAdmin sporterAdmin;
    Sporter sporter;
    Goal goal;
    Workout workout;
    Exercise exercise;
    LoginBean loginBean;
    Tag tag;

    @BeforeEach
    void setUp() {
        sporterAdmin = new SporterAdmin(new SporterStoreDemo());
        sporter = sporterAdmin.getAllSporters().get(0);
        Exercise goalExercise = new Exercise(0, "Implement unit tests", 30, 6, "ut");
        List<Exercise> allExercises = new ArrayList<>();
        allExercises.add(goalExercise);
        tag = new Tag("#test");
        goal = new Goal(0,"Test goal","Test goal description",tag, allExercises);
        workout = new Workout(0,"Test workout", Weekday.THURSDAY, Status.ACTIVE);
        exercise = new Exercise(0,"Implement unit tests", 3, 6, "ut");
        loginBean = new LoginBean("nkorporaal", "traino213");
    }

    @Test
    void getAllSporters() {
        //Assign
        sporterAdmin.addSporter(sporter);

        //Act
        List<Sporter> allSporters = sporterAdmin.getAllSporters();

        //Assert
        assertEquals(allSporters, sporterAdmin.getAllSporters());
    }


    @Test
    void addSporter() {
        //Assign

        //Act
        sporterAdmin.addSporter(sporter);

        //Assert
        assertEquals(sporter, sporterAdmin.getAllSporters().get(sporterAdmin.getAllSporters().size() - 1));
    }

    @Test
    void getAllGoals() {
        //Assign
        sporterAdmin.login(loginBean);

        sporterAdmin.addGoal(goal);

        //Act
        List<Goal> allGoals = sporterAdmin.getAllGoals();

        //Assert
        assertEquals(allGoals, sporterAdmin.getAllGoals());
    }

    @Test
    void addGoal() { //D08_1
        //Assign

        //Act
        sporterAdmin.addGoal(goal);

        //Assign
        assertEquals(goal, sporterAdmin.getAllGoals().get(sporterAdmin.getAllGoals().size() - 1));
    }

    @Test
    void login() { //U01_0
        //Assign
        loginBean = new LoginBean("nkorporaal", "traino213");

        //Act
        Sporter sporterTest = sporterAdmin.login(loginBean);

        //Assert
        assertEquals(sporter, sporterTest);
    }

    @Test
    void loginWithoutPassword() { //U01_1
        //Assign
        loginBean = new LoginBean("nkorporaal", "");

        //Act
        Sporter sporter = sporterAdmin.login(loginBean);

        //Assert
        assertEquals(null, sporter);
    }

    @Test
    void loginWithoutUsernameAndPassword() { //U01_2
        //Assign
        LoginBean loginBean = new LoginBean("", "");

        //Act
        Sporter sporter = sporterAdmin.login(loginBean);

        //Assert
        assertEquals(null, sporter);
    }

    @Test
    void logout() {
        //Assign
        sporterAdmin.login(loginBean);

        //Act
        sporterAdmin.logout();

        //Assert
        assertEquals(null, sporterAdmin.getLoggedInSporter());
    }

    @Test
    void getAllExercises() {
        //Assign
        workout.addExercise(exercise);
        sporterAdmin.addWorkout(workout, null);

        //Act
        List<Exercise> allExercises = sporterAdmin.getAllExercises(workout);

        //Assert
        assertEquals(exercise, allExercises.get(0));
    }

    @Test
    void addGoalExercise() {
        //Assign
        sporterAdmin.addGoal(goal);

        //Act
        sporterAdmin.addGoalExercise(goal,exercise);

        //Assert
        assertEquals(exercise, sporterAdmin.getAllGoals().get(sporterAdmin.getAllGoals().size()-1).getAllExercises().get(1));
    }

    @Test
    void addWorkoutExercise() {
        //Assign
        sporterAdmin.addWorkout(workout,null);

        //Act
        sporterAdmin.addWorkoutExercise(workout, exercise);

        //Assert
        assertEquals(exercise, sporterAdmin.getAllWorkouts().get(sporterAdmin.getAllWorkouts().size()-1).getAllExercises().get(0));
    }

    @Test
    void getSuggestions() {
        //Assign
        sporterAdmin.addGoal(goal);
        sporterAdmin.addWorkout(workout, null);
        sporterAdmin.addWorkoutExercise(workout, exercise);

        //Act
        List<Workout> suggestions = sporterAdmin.getSuggestions(goal);

        Workout workout = suggestions.get(0);
        Exercise exercise2 = workout.getAllExercises().get(0);

        //Assert
        assertEquals(exercise2, suggestions.get(0).getAllExercises().get(0));
    }


}