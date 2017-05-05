package learn.com.smssender.screen.controller;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import learn.com.smssender.dataaccess.SenderDataAccess;
import learn.com.smssender.dataaccess.callback.SenderDACallBack;
import learn.com.smssender.model.Sender;
import learn.com.smssender.screen.MainActivity;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class MainController{

    private MainActivity activity;
    private List<Sender> data;
    private SenderDataAccess dataAccess;

    public MainController(MainActivity activity) {
        this.activity = activity;
        this.dataAccess = new SenderDataAccess();
        loadDataForFirstTime();
    }

    private void loadDataForFirstTime() {
        dataAccess.getAllSenders(new SenderDACallBack<List<Sender>>() {
            @Override
            public void onSuccess(List<Sender> data) {
                MainController.this.data = data;
                activity.configureRecyclerView(data);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(activity,"Error: "+message,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
