package models;

import com.sun.istack.NotNull;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.JOINED)
public class Human implements EntityClass {

    private long id;
    private StringProperty name = new SimpleStringProperty();

    public Human() {
        this.name.set("");
    }

    public Human(String name) {
        this.name.set(name);
    }

    @NotNull
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
    @GeneratedValue(generator = "sqlite")
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
