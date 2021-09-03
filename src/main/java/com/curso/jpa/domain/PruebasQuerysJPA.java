package com.curso.jpa.domain;

import java.util.List;

import javax.persistence.*;


public class PruebasQuerysJPA {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("OracleHRPU");
	
	public static void main(String[] args) {
		EntityManager em = factory.createEntityManager();
		String consulta = "SELECT e FROM Empleado e";
		Query query = em.createQuery(consulta);
		List<Empleado> lista = query.getResultList();
		
		for(Empleado e:lista) {
			System.out.println("ID: "+e.getId()+", Nombre: "+e.getFirst_name()+", Apellidos: "+e.getLast_name());
		}

	}

}
