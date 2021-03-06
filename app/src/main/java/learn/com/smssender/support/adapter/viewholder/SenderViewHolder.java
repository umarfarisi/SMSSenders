package learn.com.smssender.support.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import learn.com.smssender.R;
import learn.com.smssender.model.Sender;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class SenderViewHolder extends RecyclerView.ViewHolder {

    private TextView senderNameTextView;
    private TextView senderNumberTextView;
    private TextView senderNumberOfSendingTextView;

    public SenderViewHolder(View itemView) {
        super(itemView);
        loadViews();
    }

    private void loadViews() {
        senderNameTextView = (TextView) itemView.findViewById(R.id.senderNameTextView);
        senderNumberTextView = (TextView) itemView.findViewById(R.id.senderNumberTextView);
        senderNumberOfSendingTextView = (TextView) itemView.findViewById(R.id.senderNumberOfSendingTextView);
    }

    public void setUpData(Sender sender){
        senderNameTextView.setText(sender.getName());
        senderNumberTextView.setText(sender.getNumber());
        senderNumberOfSendingTextView.setText(String.valueOf(sender.getNumberOfSending()));
    }

}
