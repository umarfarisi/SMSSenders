package learn.com.smssender.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import learn.com.smssender.R;
import learn.com.smssender.support.adapter.SendersAdapter;
import learn.com.smssender.model.Sender;
import learn.com.smssender.screen.controller.MainController;

public class MainActivity extends AppCompatActivity {

    private RecyclerView sendersRecyclerView;
    private TextView emptyTextView;
    private ProgressBar progressBar;
    private SendersAdapter sendersAdapter;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadViews();
        setUpDefaultValue();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isControllerNotNull()){
            controller.loadData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isControllerNotNull()){
            controller.onDestroy();
        }
    }

    private void loadViews() {
        sendersRecyclerView = (RecyclerView) findViewById(R.id.sendersRecyclerView);
        emptyTextView = (TextView) findViewById(R.id.emptyTextView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void setUpDefaultValue() {
        controller = new MainController(this);
        sendersAdapter = new SendersAdapter(this);
        sendersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sendersRecyclerView.setAdapter(sendersAdapter);
    }

    public TextView getEmptyTextView() {
        return emptyTextView;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public SendersAdapter getSendersAdapter() {
        return sendersAdapter;
    }

    public RecyclerView getSendersRecyclerView() {
        return sendersRecyclerView;
    }

    private boolean isControllerNotNull() {
        return controller != null;
    }

}
