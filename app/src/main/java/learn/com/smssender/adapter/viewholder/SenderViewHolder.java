package learn.com.smssender.adapter.viewholder;

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
    private TextView senderNumberOfSendingTextView;

    public SenderViewHolder(View itemView) {
        super(itemView);
        loadViews();
    }

    private void loadViews() {
        senderNameTextView = (TextView) itemView.findViewById(R.id.senderNameTextView);
        senderNumberOfSendingTextView = (TextView) itemView.findViewById(R.id.senderNumberOfSendingTextView);
    }

    public void setUpData(Sender sender){
        senderNameTextView.setText(sender.getName());
        senderNumberOfSendingTextView.setText(sender.getNumberOfSending());
    }

}
