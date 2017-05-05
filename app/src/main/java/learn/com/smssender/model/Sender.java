package learn.com.smssender.model;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class Sender {

    private String name;
    private String number;
    private int numberOfSending;

    public Sender(String name, String number, int numberOfSending) {
        this.name = name;
        this.number = number;
        this.numberOfSending = numberOfSending;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumberOfSending() {
        return numberOfSending;
    }

    public void setNumberOfSending(int numberOfSending) {
        this.numberOfSending = numberOfSending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sender sender = (Sender) o;

        return number.equals(sender.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return "Sender{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", numberOfSending=" + numberOfSending +
                '}';
    }
}
