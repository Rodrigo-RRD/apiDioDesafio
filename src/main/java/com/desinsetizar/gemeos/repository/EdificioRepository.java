package com.desinsetizar.gemeos.repository;

import org.springframework.data.repository.CrudRepository;

import com.desinsetizar.gemeos.models.Edificio;

public interface EdificioRepository extends CrudRepository<Edificio, String> {
    Edificio findById(int id);

}
