package learn.com.smssender.dataaccess;

import java.util.ArrayList;
import java.util.List;

import learn.com.smssender.dataaccess.callback.SenderDACallBack;
import learn.com.smssender.model.Sender;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class SenderDataAccess {
    public void getAllSenders(SenderDACallBack<List<Sender>> getAllSendersCallback){
        List<Sender> data = new ArrayList<>();
        for(int i = 50 ; i > 0 ; i--){
            data.add(new Sender("Nama "+i,i));
        }
        getAllSendersCallback.onSuccess(data);
    }
}
