package com.curso.jpa.jpa;

import java.util.List;

import javax.persistence.*;

import com.curso.jpa.domain.Department;
import com.curso.jpa.domain.Empleado;
import com.curso.jpa.domain.Trabajo;

public class EmpleadoDao {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("OracleHRPU");
	
	
	public static void main(String[] args) {
		 
		EmpleadoDao dao = new EmpleadoDao();

	     Empleado e = new Empleado();
	     
	        e.setFirst_name("Luis");
	        e.setLast_name("Ramos");
	        e.setEmail("ramos@email.com");
	        e.setPhone_number("602822566");
	        e.setHire_date(new java.util.Date());
	        e.setJob_id("SA_MAN");
	        e.setSalary(23000.5);
	     //   e.setCommission_pct(10.0);
	        e.setManager_id(100);
	        e.setDepartment_id(100);
	        
        //TODOS FUNCIONAN
	        // dao.crearEmpleado(e); 
	        //dao.getEmpleado(188L); 
	        // dao.eliminarEmpleado(206L); 
	        //Empleado dos = dao.getEmpleado(203L);
	        //dos.setFirst_name("Mariano");
	        //dao.modificarEmpleado(dos);
	        
	        Empleado empleado = dao.getEmpleado(100L);
	        System.out.println("Nombre empleado: "+ empleado.getFirst_name());
	        System.out.println("Trabajo: "+ empleado.getTrabajo().getTitulo());
	        System.out.println();
	        dao.getTrabajo("SA_MAN");
	        System.out.println("....................");
	        System.out.println("Lista General de Empleados");
	        
	       List<Empleado> lista = dao.getEmplPorTrabajo("SA_MAN");
	       for(Empleado emp :lista) {
	    	   System.out.println("ID: "+emp.getId()+", Nombre: "+emp.getFirst_name()+", Apellidos: "+emp.getLast_name());
	    	   System.out.println("Puesto: "+emp.getTrabajo().getTitulo());
	    	   System.out.println();
	       }
	}
	
	
	public void crearEmpleado(Empleado empleado) {
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//El empleado aun no está en conectado a la base de datos
		em.persist(empleado);	//lanza excepcion de tipo RunTimeException
		//El empleado ya esta administrado en la base de datos
		tx.commit();
	}
	
	//Buscar Empleado
	public Empleado getEmpleado(Long id) {
		
		EntityManager em = factory.createEntityManager();
		Empleado e = em.find(Empleado.class, id);
		return e;
	}
	//Modificar Empleado
	public void modificarEmpleado(Empleado empleadoNoBD) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Empleado empleadoBD = em.merge(empleadoNoBD);
		tx.commit();
		
	}
	//Eliminar Empleado
	public void eliminarEmpleado(Long id) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Empleado empleadoBD = em.find(Empleado.class, id);
		em.remove(empleadoBD);
		tx.commit();
	}
	
	//Buscar trabajo y mostrar sus empleados
	public Trabajo getTrabajo(String id) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Trabajo t = em.find(Trabajo.class, id);
		System.out.println("Lista de Empleados de la sección: "+t.getTitulo());
		List<Empleado> l = t.getColeccionEmpleados();
		for(Empleado e:l) {
			System.out.println("Empleado: "+e.getFirst_name()+" "+e.getLast_name());
		}
		return t;
	}
	
	/*Metodo para cargar la lista de todos los empleados*/
	/*CONSULTAS DINAMICAS*/
	public List<Empleado> getAllEmpleados(){
		
		EntityManager em = factory.createEntityManager();
		
		//QUERY DINAMICA 
			String consulta = "SELECT e FROM Empleado e";
			//Query query = em.createQuery(consulta);	//FUNCIONA
		
		//NAMED QUERY //FUNCIONA
			Query query = em.createNamedQuery("Empleado.finAll");
		
		//NATIVE QUERY
			//Query query = em.createNativeQuery("SELECT * FROM EMPLOYEES");
		List<Empleado> lista = query.getResultList();
		return lista;
		
	}
	
	/*Metodo que devuelve los empleados de un trabajo*/
	public List<Empleado> getEmplPorTrabajo(String idTrabajo){
		EntityManager em = factory.createEntityManager();
		Query query = em.createNamedQuery("Empleado.findByTrabajoId");
		query.setParameter("job_id", idTrabajo);
		List<Empleado> lista = query.getResultList();
		return lista;
	}
}
