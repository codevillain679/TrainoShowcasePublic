import com.traino.app.Status;
import com.traino.app.Weekday;
import com.traino.app.Workout;
import com.traino.app.admin.ScheduleAdmin;
import com.traino.app.interfaces.Schedulable;
import com.traino.datastoredemo.ScheduleStoreDemo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleAdminTest {

    ScheduleAdmin scheduleAdmin;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        scheduleAdmin = new ScheduleAdmin(new ScheduleStoreDemo());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void addScheduleItem(){
        //Assign
        Schedulable item = new Workout(0, "Back and shoulders TABATA", Weekday.THURSDAY, Status.ACTIVE);

        //Act
        scheduleAdmin.addScheduleItem(item);

        //Assert
        assertEquals(item, scheduleAdmin.getAllScheduleItems().get(scheduleAdmin.getAllScheduleItems().size() - 1));
    }

    @Test
    void getAllScheduleItems() {

    }
}