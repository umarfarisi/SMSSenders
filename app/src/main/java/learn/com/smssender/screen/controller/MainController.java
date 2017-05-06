package learn.com.smssender.screen.controller;

import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import learn.com.smssender.presenter.dataaccess.SenderDataAccess;
import learn.com.smssender.presenter.dataaccess.callback.SenderDACallBack;
import learn.com.smssender.model.Sender;
import learn.com.smssender.presenter.receiver.event.IncomingSMSEvent;
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
        EventBus.getDefault().register(this);
    }

    public void loadData() {
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

    public void onDestroy(){
        EventBus.getDefault().unregister(this);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onIncomingSMS(IncomingSMSEvent event){
        if(data.contains(new Sender(event.getSenderNumber(),event.getSenderNumber(),1))){
            Sender currentSender = data.get(data.indexOf(new Sender(null,event.getSenderNumber(),-1)));
            currentSender.setNumberOfSending(currentSender.getNumberOfSending()+1);
        }else{
            data.add(new Sender(event.getSenderNumber(),event.getSenderNumber(),1));
        }
        activity.getSendersAdapter().notifyDataSetChanged();
    }

}
