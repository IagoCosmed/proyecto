package org.zkoss.zkspringboot.service.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.zkoss.zkspringboot.entity.Plato;
import org.zkoss.zkspringboot.repository.PlatoRepository;
import org.zkoss.zkspringboot.service.PlatoService;
import org.zkoss.zkspringboot.specification.PlatoSpecification;

@Service
public class PlatoServiceImpl implements PlatoService {
	
	
	private PlatoRepository platoRepository; 
	
	@Override
	public Plato addPlato(String nombre, double precio, String ingredientes) {
		Plato plato = new Plato();
		plato.setNombre(nombre);
		plato.setPrecio(precio);
		plato.setIngredientes(ingredientes);
		return platoRepository.save(plato);
	}

	@Override
	public Plato findPlatoByNombrePlato(String nombre) {
		
		return platoRepository.findByNombre(nombre).orElse(null);
	}

	@Override
	public List<Plato> listOfPlato() {
		
		return platoRepository.findAll();
	}
	
	public List<Plato> getPlatoWhereTitleIsNotLikeAnyWord(List<String> words) {
	    if(words.isEmpty()) {
	        return platoRepository.findAll();
	    }

	    Specification<Plato> specification = null;   
	    for(String word : words) {
	       Specification<Plato> wordSpecification = PlatoSpecification.tituloIsNotLike(word);
	       if(specification == null) {
	           specification = wordSpecification;
	       } else {
	           specification = specification.or(wordSpecification);
	       }
	    }

	    return platoRepository.findAll(specification);
	}

}
