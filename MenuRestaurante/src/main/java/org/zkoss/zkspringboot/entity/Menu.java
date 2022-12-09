package org.zkoss.zkspringboot.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;


@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "menu_id")
	private Long id;
	@Column( name = "nombre")
	private String nombre;
	@Column( name = "precio")
	private double precio;
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="nombre")
	private List<Plato> platoList;
	
	
	public Menu() {
		super();
	}
	
	public Menu(String nombre, double precio, List<Plato> platoList ) {
		this.nombre = nombre;
		this.precio = precio;
		this.platoList = platoList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Plato> getPlatoList() {
		return platoList;
	}

	public void setPlatoList(List<Plato> platoList) {
		this.platoList = platoList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, platoList, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(platoList, other.platoList)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", platoList=" + platoList + "]";
	}
	
	
	
}
