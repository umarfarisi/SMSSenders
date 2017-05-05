package learn.com.smssender.screen.controller;

import android.view.View;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
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
        activity.getProgressBar().setVisibility(View.VISIBLE);
        dataAccess.getAllSenders(new SenderDACallBack<List<Sender>>() {
            @Override
            public void onSuccess(List<Sender> data) {
                activity.getProgressBar().setVisibility(View.GONE);
                MainController.this.data = data;
                if(!data.isEmpty()) {
                    Collections.sort(data, new Comparator<Sender>() {
                        @Override
                        public int compare(Sender sender1, Sender sender2) {
                            return sender2.getNumberOfSending() - sender1.getNumberOfSending();
                        }
                    });
                    activity.getEmptyTextView().setVisibility(View.GONE);
                }else{
                    activity.getEmptyTextView().setVisibility(View.VISIBLE);
                }
                activity.configureRecyclerView(data);
            }

            @Override
            public void onError(String message) {
                activity.getProgressBar().setVisibility(View.GONE);
                Toast.makeText(activity,"Error: "+message,Toast.LENGTH_SHORT).show();
            }
        });
    }



}
