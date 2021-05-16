package SIS;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MarksData {
    private final StringProperty subject1;
    private final StringProperty subject2;
    private final StringProperty subject3;
    private final StringProperty subject4;

    public MarksData(String subject1, String subject2, String subject3, String subject4) {
        this.subject1 = new SimpleStringProperty(subject1);
        this.subject2 = new SimpleStringProperty(subject2);
        this.subject3 = new SimpleStringProperty(subject3);
        this.subject4 = new SimpleStringProperty(subject4);
    }

    public String getSubject1() {
        return subject1.get();
    }

    public StringProperty subject1Property() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1.set(subject1);
    }

    public String getSubject2() {
        return subject2.get();
    }

    public StringProperty subject2Property() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2.set(subject2);
    }

    public String getSubject3() {
        return subject3.get();
    }

    public StringProperty subject3Property() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3.set(subject3);
    }

    public String getSubject4() {
        return subject4.get();
    }

    public StringProperty subject4Property() {
        return subject4;
    }

    public void setSubject4(String subject4) {
        this.subject4.set(subject4);
    }
}
