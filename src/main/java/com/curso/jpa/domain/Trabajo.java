package com.curso.jpa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="JOBS")
public class Trabajo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//Pendiente de generatedValue
	@Column(name="JOB_ID")
	private String id;
	
	@Column(name="JOB_TITLE", nullable = false)
	private String titulo;
	
	@Column(name="MIN_SALARY")
	private Double salarioMin;
	
	@Column(name="MAX_SALARY")
	private Double salarioMax;
	
	//Cuando queremos ver los empleados que ejecutan este trabajo
	@OneToMany(mappedBy = "trabajo")// (cascade = CascadeType.REMOVE) opcional
	private List<Empleado> coleccionEmpleados;

	public Trabajo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getSalarioMin() {
		return salarioMin;
	}

	public void setSalarioMin(Double salarioMin) {
		this.salarioMin = salarioMin;
	}

	public Double getSalarioMax() {
		return salarioMax;
	}

	public void setSalarioMax(Double salarioMax) {
		this.salarioMax = salarioMax;
	}
	
	

	public List<Empleado> getColeccionEmpleados() {
		return coleccionEmpleados;
	}

	public void setColeccionEmpleados(List<Empleado> coleccionEmpleados) {
		this.coleccionEmpleados = coleccionEmpleados;
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
		Trabajo other = (Trabajo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trabajo [id=" + id + ", titulo=" + titulo + ", salarioMin=" + salarioMin + ", salarioMax=" + salarioMax
				+ "]";
	}
	
	

}
