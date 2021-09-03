package com.curso.jpa.manyTomany;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="GRUPOS")
public class Grupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", insertable = false)
	private Integer id;
	
	@Column(name="COLOR")
	private String color;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@ManyToMany(mappedBy = "grupoCollection", 
            fetch = FetchType.LAZY)
	private Collection<Contacto> contactoCollection;

	public Grupo() {
		super();
	}

	public Grupo(Integer id) {
		super();
		this.id = id;
	}

	public Grupo(Integer id, String color, String nombre, Collection<Contacto> contactoCollection) {
		super();
		this.id = id;
		this.color = color;
		this.nombre = nombre;
		this.contactoCollection = contactoCollection;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Contacto> getContactoCollection() {
		return contactoCollection;
	}

	public void setContactoCollection(Collection<Contacto> contactoCollection) {
		this.contactoCollection = contactoCollection;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", color=" + color + ", nombre=" + nombre + ", contactoCollection="
				+ contactoCollection + "]";
	}
	
	
	
}
