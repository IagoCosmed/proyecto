package org.zkoss.zkspringboot.specification;

import javax.persistence.criteria.Expression;

import org.springframework.data.jpa.domain.Specification;
import org.zkoss.zkspringboot.entity.Plato;

public class PlatoSpecification {
	public static Specification<Plato> tituloIsNotLike(String searchWord) {
		return (root, query, builder) -> {	Expression<String> titleLowerCase = builder.lower(root.get("titulo"));
        return builder.notLike(titleLowerCase, "%" + searchWord.toLowerCase() + "%");};
	}
}