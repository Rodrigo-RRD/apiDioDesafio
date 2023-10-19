package com.desinsetizar.gemeos.repository;

import org.springframework.data.repository.CrudRepository;

import com.desinsetizar.gemeos.models.Edificio;
import com.desinsetizar.gemeos.models.Morador;

public interface MoradorRepository extends CrudRepository<Morador, String> {

    Iterable<Morador> findByEdificio(Edificio edificio);

    Morador findByApartamento(int apartamento);

}