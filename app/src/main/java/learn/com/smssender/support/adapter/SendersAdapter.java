package learn.com.smssender.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import learn.com.smssender.R;
import learn.com.smssender.support.adapter.viewholder.SenderViewHolder;
import learn.com.smssender.model.Sender;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class SendersAdapter extends RecyclerView.Adapter<SenderViewHolder> {

    private Context context;
    private List<Sender> senders;

    public SendersAdapter(Context context) {
        this.context = context;
    }

    public void setUpData(List<Sender> senders){
        this.senders = senders;
    }

    @Override
    public SenderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SenderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sender,parent,false));
    }

    @Override
    public void onBindViewHolder(SenderViewHolder holder, int position) {
        holder.setUpData(senders.get(position));
    }

    @Override
    public int getItemCount() {
        if(senders.size() < 10){
            return senders.size();
        }
        return 10;
    }
}
