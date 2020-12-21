import com.traino.app.Status;
import com.traino.app.Weekday;
import com.traino.app.Workout;
import com.traino.app.admin.ScheduleAdmin;
import com.traino.app.interfaces.Schedulable;
import com.traino.app.interfaces.ScheduleProvider;
import com.traino.datastore.ScheduleStoreDemo;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Schedulable item = new Workout("Back and shoulders TABATA", Weekday.THURSDAY, Status.ACTIVE);

        //Act
        scheduleAdmin.addScheduleItem(item);

        //Assert
        assertEquals(item, scheduleAdmin.getAllScheduleItems().get(scheduleAdmin.getAllScheduleItems().size() - 1));
    }

    @Test
    void getAllScheduleItems() {

    }
}