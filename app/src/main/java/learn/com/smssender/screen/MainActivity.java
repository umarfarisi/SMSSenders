package learn.com.smssender.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import learn.com.smssender.R;
import learn.com.smssender.adapter.SendersAdapter;
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

    private void setUpDefaultValue() {
        controller = new MainController(this);
    }

    private void loadViews() {
        sendersRecyclerView = (RecyclerView) findViewById(R.id.sendersRecyclerView);
        emptyTextView = (TextView) findViewById(R.id.emptyTextView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void configureRecyclerView(List<Sender> senders){
        sendersAdapter = new SendersAdapter(this,senders);
        sendersRecyclerView.setAdapter(sendersAdapter);
        sendersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
}
