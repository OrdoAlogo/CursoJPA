package com.curso.jpa.manyTomany;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PruebasManyToMany {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("OracleHRPU");

	public static void main(String[] args) {
		PruebasManyToMany mtm = new PruebasManyToMany();
		
		Contacto ane = new Contacto();
		ane.setNombre("Karen");
		ane.setApellidos("Miranda");
		ane.setEmail("karen@gmail.com");
		ane.setFecha_n(new java.util.Date());
		//mtm.crearContacto(ane); //Funciona
		
		Grupo becarios = new Grupo();
		becarios.setNombre("Becarios");
		becarios.setColor("Azul");
		
		Grupo profesores = new Grupo();
		profesores.setNombre("Profesores");
		profesores.setColor("Verde");
		
		//mtm.crearGrupo(becarios); //Funciona
		//mtm.crearGrupo(profesores); //Funciona
		
		mtm.añadirGrupo(1, 1);
		
	}
	
	public void crearContacto(Contacto contacto) {
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(contacto);
		tx.commit();
	}
	
	public void crearGrupo(Grupo grupo) {
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(grupo);
		tx.commit();
	}
	
	public void añadirGrupo(Integer idC, Integer idG) {
		
		//Primero obtengo el id del contacto
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Contacto c = em.find(Contacto.class, idC);
		Grupo g = em.find(Grupo.class, idG);
		
		c.getGrupoCollection().add(g);
		tx.commit();
	}
	
	

}
