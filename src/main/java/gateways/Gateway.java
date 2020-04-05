package gateways;

import exceptions.EntityNotFound;

import java.util.List;

public interface Gateway<T> {
    List<T> all();
    T find(Long id) throws EntityNotFound;
    void insert(T object);
    void update(T object) throws EntityNotFound;
    void delete(T obj);
}
