package com.curso.jpa.manyTomany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PruebasManyToMany {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("OracleHRPU");

	public static void main(String[] args) {
		PruebasManyToMany mtm = new PruebasManyToMany();
		
		Collection<Grupo> coleccionGrupos = null;
		List<Contacto> listaContactos = new ArrayList<Contacto>();
		listaContactos.add(new Contacto(0,"Teofilo","Nguema","teo@nguema.com",new java.util.Date(),coleccionGrupos));
		listaContactos.add(new Contacto(0,"Nuria","Alogo","nuria@alogo.com",new java.util.Date(),coleccionGrupos));
		listaContactos.add(new Contacto(0,"Silvia","Cristina","silvia@cristina.com",new java.util.Date(),coleccionGrupos));
		listaContactos.add(new Contacto(0,"Evangelina","Esomoyo","eva@gmail.com",new java.util.Date(),coleccionGrupos));
		listaContactos.add(new Contacto(0,"Sandra Jesus","Nchama","sandra@jesus.com",new java.util.Date(),coleccionGrupos));
		listaContactos.add(new Contacto(0,"Carlos Javier","Nguema","andy@gmail.com",new java.util.Date(),coleccionGrupos));
		
		//FUNCIONA
//		for(int i = 0; i<listaContactos.size(); i++) {
//			mtm.crearContacto(listaContactos.get(i));
//			System.out.println(listaContactos.get(i).getNombre()+" "+listaContactos.get(i).getApellidos()+", Añadido a la Base de datos");
//		}
		
		System.out.println("....Sección de los Grupos....");
		Collection<Contacto> coleccionContactos = null;
		List<Grupo> listaGrupos = new ArrayList<Grupo>();
		listaGrupos.add(new Grupo(0,"Naranja","Familia",coleccionContactos));
		listaGrupos.add(new Grupo(0,"Gris","Barrio",coleccionContactos));
		listaGrupos.add(new Grupo(0,"Rosa","Madres",coleccionContactos));
		
		//FUNCIONA Pero al final me saca un error
//		for(int i = 0; i<listaContactos.size(); i++) {
//			mtm.crearGrupo(listaGrupos.get(i));
//			System.out.println("El gurpo: "+listaGrupos.get(i).getNombre()+" ha sido Añadido a la Base de datos");
//		}
		
		//mtm.crearGrupo(becarios); //Funciona
		//mtm.crearGrupo(profesores); //Funciona
		
		//mtm.añadirContactoAlGrupo(2, 25);	//FUNCIONA
//		System.out.println(mtm.buscarContacto(27).getNombre());	//FUNCIONA
//		System.out.println(mtm.buscarGrupo(2).getNombre());	//FUNCIONA
	}
	
	//METODO PARA AÑADIR CONTACTO A LA BD
	public void crearContacto(Contacto contacto) {
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(contacto);
		tx.commit();
	}
	//METODO PARA AÑADIR GRUPO A LA BD
	public void crearGrupo(Grupo grupo) {
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(grupo);
		tx.commit();
	}
	
	public void añadirContactoAlGrupo(Integer idC, Integer idG) {
		
		//Primero obtengo el id del contacto
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Grupo g = em.find(Grupo.class, idG);
		Contacto c = em.find(Contacto.class, idC);
		g.getContactoCollection().add(c);
		tx.commit();
	}
	
	public Contacto buscarContacto(int id) {
		EntityManager em = factory.createEntityManager();
		Contacto contacto = em.find(Contacto.class, id);
		return contacto;
	}
	public Grupo buscarGrupo(int id) {
		EntityManager em = factory.createEntityManager();
		Grupo grupo = em.find(Grupo.class, id);
		return grupo;
	}
	
	

}
