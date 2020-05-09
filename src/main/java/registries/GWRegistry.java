package registries;


import gateways.AnimalGateway;
import gateways.ClientGateway;
import gateways.Gateway;
import gateways.HumanGateway;
import models.Animal;
import models.Client;
import models.Human;

public class GWRegistry {
    private Gateway<Human> humanGateway = new HumanGateway();
    private Gateway<Animal> animalGateway = new AnimalGateway();
    private Gateway<Client> clientGateway = new ClientGateway();

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

    public Gateway<Client> getClientGateway() {
        return clientGateway;
    }
}