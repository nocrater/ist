package registries;


import gateways.AnimalGateway;
import gateways.Gateway;
import gateways.HumanGateway;
import models.Animal;
import models.Human;

public class GWRegistry {
    private Gateway<Human> humanGateway = new HumanGateway();
    private Gateway<Animal> animalGateway = new AnimalGateway();

    private static GWRegistry instance = new GWRegistry();

    private GWRegistry() {
    }

    public static GWRegistry getInstance() {
        return instance;
    }

    public Gateway<Human> getHumanGateway() {
        return humanGateway;
    }

    public Gateway<Animal> getAnimalGateway() {
        return animalGateway;
    }
}