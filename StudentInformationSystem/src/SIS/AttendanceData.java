package SIS;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AttendanceData {
//    private final StringProperty id;
    private final StringProperty sub1;
    private final StringProperty sub2;
    private final StringProperty sub3;
    private final StringProperty sub4;


    public AttendanceData(String sub1, String sub2, String sub3, String sub4) {
//        this.id = id;
        this.sub1 = new SimpleStringProperty(sub1);
        this.sub2 = new SimpleStringProperty(sub2);
        this.sub3 = new SimpleStringProperty(sub3);
        this.sub4 = new SimpleStringProperty(sub4);
    }


//    public String getId() {
//        return this.idProperty().get();
//    }
//
//    public StringProperty idProperty() {
//        return this.id;
//    }
//
//    public void setId(String id) {
//        this.idProperty().set(id);
//    }

    public String getSub1() {
        return sub1Property().get();
    }

    public StringProperty sub1Property() {
        return this.sub1;
    }

    public void setSub1(String sub1) {
        this.sub1Property().set(sub1);
    }

    public String getSub2() {
        return sub2Property().get();
    }

    public StringProperty sub2Property() {
        return this.sub2;
    }

    public void setSub2(String sub2) {
        this.sub2Property().set(sub2);
    }

    public String getSub3() {
        return sub3Property().get();
    }

    public StringProperty sub3Property() {
        return this.sub3;
    }

    public void setSub3(String sub3) {
        this.sub3Property().set(sub3);
    }

    public String getSub4() {
        return sub4Property().get();
    }

    public StringProperty sub4Property() {
        return this.sub4;
    }

    public void setSub4(String sub4) {
        this.sub4Property().set(sub4);
    }



}
