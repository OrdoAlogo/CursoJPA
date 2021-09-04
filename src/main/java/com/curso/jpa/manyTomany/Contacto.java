package com.curso.jpa.manyTomany;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="CONTACTOS")
public class Contacto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", insertable = false)
	private int id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="APELLIDOS")
	private String apellidos;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="FECHA_NAC")
	@Temporal(TemporalType.DATE)
	private Date fecha_n;
		    
	@ManyToMany(mappedBy="contactoCollection", fetch = FetchType.LAZY)
    private Collection<Grupo> grupoCollection;

	public Contacto() {
		super();
	}
	public Contacto(int id) {
		super();
		this.id = id;
	}
	public Contacto(int id, String nombre, String apellidos, String email, Date fecha_n,
			Collection<Grupo> grupoCollection) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fecha_n = fecha_n;
		this.grupoCollection = grupoCollection;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFecha_n() {
		return fecha_n;
	}
	public void setFecha_n(Date fecha_n) {
		this.fecha_n = fecha_n;
	}
	public Collection<Grupo> getGrupoCollection() {
		return grupoCollection;
	}
	public void setGrupoCollection(Collection<Grupo> grupoCollection) {
		this.grupoCollection = grupoCollection;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Contacto other = (Contacto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Contacto [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", fecha_n=" + fecha_n + ", grupoCollection=" + grupoCollection + "]";
	}
	
	

}
