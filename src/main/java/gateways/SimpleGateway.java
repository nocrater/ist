package gateways;

import exceptions.EntityNotFound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleGateway<T> implements Gateway<T>{

    static long id = 0;

    protected Map<Long, T> db = new HashMap<Long, T>();

    @Override
    public List<T> all() {
        return new ArrayList<T>(db.values());
    }

    @Override
    public T find(Long id) throws EntityNotFound {
        if (db.containsKey(id))
            throw new EntityNotFound(String.format("Entity with id=%d not found", id ));
        return db.get(id);
    }

    @Override
    public void insert(T object) {
        db.put(id++, object);
    }

    @Override
    public void update(T object) throws EntityNotFound {
        if (db.containsKey(id))
            throw new EntityNotFound(String.format("Entity with id=%d not found", id ));
        db.put(id, object);
    }

    @Override
    public void delete(T ob) {
        db.remove(ob);
    }
}
