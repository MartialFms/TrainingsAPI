package fr.fms.apitrainings.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    public List<T> getAll();

    public Optional<T> getOneById(long id);

    public T save(T obj);

    public void delete(long id);

}
