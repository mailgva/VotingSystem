package com.voting.repository.datajpa;

import com.voting.model.Resto;
import com.voting.repository.RestoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataJpaRestoRepositoryImpl implements RestoRepository {

    @Autowired
    private CrudRestoRepository crudRestoRepository;

    @Override
    public Resto save(Resto resto) {
        return crudRestoRepository.save(resto);
    }

    @Override
    public boolean delete(int id) {
        return crudRestoRepository.delete(id) != 0;
    }

    @Override
    public Resto get(int id) {
        return crudRestoRepository.getOne(id);
    }

    @Override
    public Resto getByName(String name) {
        return crudRestoRepository.getByName(name);
    }

    @Override
    public List<Resto> getAll() {
        return crudRestoRepository.getAll();
    }
}
