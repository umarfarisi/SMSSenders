package learn.com.smssender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import learn.com.smssender.model.Sender;

public class MainActivity extends AppCompatActivity {

    private RecyclerView sendersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendersRecyclerView = (RecyclerView) findViewById(R.id.sendersRecyclerView);

    }

    public void configureRecyclerView(List<Sender> senders){

    }

}
