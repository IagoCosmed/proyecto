package org.zkoss.zkspringboot.service.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkspringboot.entity.Plato;
import org.zkoss.zkspringboot.repository.PlatoRepository;
import org.zkoss.zkspringboot.service.PlatoService;
import org.zkoss.zkspringboot.specification.PlatoSpecification;

@Service
public class PlatoServiceImpl implements PlatoService {
	
	
	private final PlatoRepository platoRepository; 
	
	
	public PlatoServiceImpl (PlatoRepository platoRepository) {
		this.platoRepository = platoRepository;
	}


	@Override
	public List<Plato> findAll() {
		
		return platoRepository.findAll();
	}
	
	public void save(Plato plato) {
		platoRepository.save(plato);
    }
	
	public void deleteFormation(Plato plato) {
		platoRepository.delete(plato);
    }


	
	
	

}
