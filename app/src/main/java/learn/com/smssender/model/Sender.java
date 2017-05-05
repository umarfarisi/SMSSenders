package learn.com.smssender.model;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class Sender {

    private String name;
    private int numberOfSending;

    public Sender(String name, int numberOfSending) {
        this.name = name;
        this.numberOfSending = numberOfSending;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        return name.equals(sender.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Sender{" +
                "name='" + name + '\'' +
                ", numberOfSending=" + numberOfSending +
                '}';
    }
}
