import com.traino.app.*;
import com.traino.app.admin.ScheduleAdmin;
import com.traino.app.admin.SporterAdmin;
import com.traino.datastore.ScheduleStoreDemo;
import com.traino.datastore.SporterStore;
import com.traino.view.SporterView;

public class Main {
    public static void main(String[] args) {
        SporterStore dataStore = new SporterStore();
        SporterAdmin sporterAdmin = new SporterAdmin(dataStore);
        SporterView sporterView = new SporterView();

       // this is still under development
        ScheduleAdmin scheduleAdmin = new ScheduleAdmin(new ScheduleStoreDemo());
        Sporter sporter = null;

        while(sporterAdmin.getLoggedInSporter() == null){
            sporter = sporterAdmin.login(sporterView.viewLogin());
        }

        sporterView.viewProfile(sporter);

        sporterView.viewMenu(sporter);
    }
}
