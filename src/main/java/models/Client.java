package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public class Client extends Human implements EntityClass {

    @Access(AccessType.FIELD)
    @OneToMany(mappedBy = "givingClient")
    private List<Animal> givingAnimals = FXCollections.observableArrayList();

    public List<Animal> getGivingAnimals() {
        return givingAnimals;
    }

    public void setGivingAnimals(ObservableList<Animal> givingAnimals) {
        this.givingAnimals = givingAnimals;
    }

    @Access(AccessType.FIELD)
    @OneToMany(mappedBy = "takingClient")
    private List<Animal> takingAnimals = FXCollections.observableArrayList();

    public List<Animal> getTakingAnimals() {
        return takingAnimals;
    }

    public void setTakingAnimals(ObservableList<Animal> takingAnimals) {
        this.takingAnimals = takingAnimals;
    }

    public Client() {
        super();
    }

    public Client(String name) {
        super(name);
    }


}
