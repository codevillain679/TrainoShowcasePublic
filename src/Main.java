import Application.GoalStore;
import Application.SporterStore;
import Persistance.Goal;
import Persistance.ScheduleItem;
import Persistance.Sporter;
import Persistance.Workout;
import Presentation.SporterView;

public class Main {
    public static void main(String[] args) {

        Sporter sporter = new Sporter(new SporterStore());

        SporterView sporterView = new SporterView(sporter);

        sporterView.displayProfile();

        Goal goal = new Goal(new GoalStore());

        //ScheduleItem si = new Workout();

    }
}
