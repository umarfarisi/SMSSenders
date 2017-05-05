package learn.com.smssender.screen.controller;

import java.util.ArrayList;
import java.util.List;

import learn.com.smssender.model.Sender;
import learn.com.smssender.screen.MainActivity;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class MainController {

    private MainActivity activity;

    public MainController(MainActivity activity) {
        this.activity = activity;
        loadDataForFirstTime();
    }

    private void loadDataForFirstTime() {
        List<Sender> senders = new ArrayList<>();
        for(int i = 50 ; i > 0 ; i--){
            senders.add(new Sender("Nama "+i,i));
        }
        activity.configureRecyclerView(senders);
    }
}
