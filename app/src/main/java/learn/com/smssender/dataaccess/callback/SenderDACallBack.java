package learn.com.smssender.dataaccess.callback;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public interface SenderDACallBack<E> {
    void onSuccess(E data);
    void onError(String message);
}
