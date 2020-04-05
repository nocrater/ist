package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Human {

    private long id;
    private StringProperty name = new SimpleStringProperty();

    public Human(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    @Transient
    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Id
    @GeneratedValue(generator = "sqlite_human")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getName();
    }
}
