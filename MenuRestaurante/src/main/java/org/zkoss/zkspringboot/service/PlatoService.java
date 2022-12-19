package org.zkoss.zkspringboot.service;

import java.util.List;

import org.zkoss.zkspringboot.entity.Plato;
import org.zkoss.zkspringboot.repository.PlatoRepository;



public interface PlatoService {
	
	public List<Plato> findAll();
	
	
	
}
