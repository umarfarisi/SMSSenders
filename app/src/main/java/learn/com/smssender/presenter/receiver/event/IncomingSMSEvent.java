package learn.com.smssender.presenter.receiver.event;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class IncomingSMSEvent {
    private String senderNumber;

    public IncomingSMSEvent(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getSenderNumber() {
        return senderNumber;
    }
}
