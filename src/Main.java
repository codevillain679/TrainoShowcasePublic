import com.traino.app.admin.SporterAdmin;
import com.traino.datastore.SporterStore;
import com.traino.view.SporterView;


public class Main {
    private static SporterStore dataStore;
    private static SporterAdmin sporterAdmin;
    private static SporterView sporterView;

    public static void main(String[] args) {
        dataStore = new SporterStore();

        sporterAdmin = new SporterAdmin(dataStore);

        sporterView = new SporterView(sporterAdmin);

        sporterView.startApplication();
    }
}
