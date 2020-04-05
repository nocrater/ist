package registries;


import gateways.Gateway;
import gateways.HibernateHumanGateway;
import models.Human;

public class GWRegistry {
    private Gateway<Person> personGateway = new HibernateHumanGateway();

    private static GWRegistry instance = new GWRegistry();

    private GWRegistry() {
    }

    public static GWRegistry getInstance() {
        return instance;
    }

    public Gateway<Person> getHumanGateway() {
        return personHuman;
    }
}