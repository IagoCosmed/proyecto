package org.zkoss.zkspringboot.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.zkoss.zkspringboot.entity.Plato;

public interface PlatoRepository extends JpaRepository <Plato, Long>, JpaSpecificationExecutor<Plato>{
	public Optional <Plato> findByNombre(String nombre);
	public List <Plato> finAll();
	
	

}
