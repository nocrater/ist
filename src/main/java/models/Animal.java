package models;

import com.sun.istack.NotNull;
import enums.Species;
import enums.State;
import enums.WhereNow;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Animal implements EntityClass {

    private long id;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    private ObjectProperty<Species> species = new SimpleObjectProperty<>();
    private ObjectProperty<State> state = new SimpleObjectProperty<>();
    private ObjectProperty<WhereNow> whereNow = new SimpleObjectProperty<>();

    private ObjectProperty<Client> givingClient = new SimpleObjectProperty<>();
    private ObjectProperty<Client> takingClient = new SimpleObjectProperty<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "GIVING_CLIENT_ANIMAL",
            joinColumns = @JoinColumn(name = "ANIMAL_ID"), inverseJoinColumns = @JoinColumn(name = "GIVING_ANIMAL_ID"))
    public Client getGivingClient() {
        return givingClient.get();
    }

    @Transient
    public ObjectProperty<Client> givingClientProperty() {
        return givingClient;
    }

    public void setGivingClient(Client givingClient) {
        this.givingClient.set(givingClient);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "TAKING_CLIENT_ANIMAL",
            joinColumns = @JoinColumn(name = "ANIMAL_ID"), inverseJoinColumns = @JoinColumn(name = "TAKING_ANIMAL_ID"))
    public Client getTakingClient() {
        return takingClient.get();
    }

    @Transient
    public ObjectProperty<Client> takingClientProperty() {
        return takingClient;
    }

    public void setTakingClient(Client takingClient) {
        this.takingClient.set(takingClient);
    }


    public Animal() {
        this.name.set("");
        this.description.set("");
        this.species.set(Species.Cat);
        this.state.set(State.Healthy);
        this.whereNow.set(WhereNow.Housing);
    }

    public Animal(String name, String description, Species species, State state, WhereNow whereNow) {
        this.name.set(name);
        this.description.set(description);
        this.species.set(species);
        this.state.set(state);
        this.whereNow.set(whereNow);
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

    public String getDescription() {
        return description.get();
    }

    @Transient
    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Species getSpecies() {
        return species.get();
    }

    @Transient
    public ObjectProperty<Species> speciesProperty() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species.set(species);
    }

    public State getState() {
        return state.get();
    }

    @Transient
    public ObjectProperty<State> stateProperty() {
        return state;
    }

    public void setState(State state) {
        this.state.set(state);
    }

    public WhereNow getWhereNow() {
        return whereNow.get();
    }

    @Transient
    public ObjectProperty<WhereNow> whereNowProperty() {
        return whereNow;
    }

    public void setWhereNow(WhereNow whereNow) {
        this.whereNow.set(whereNow);
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