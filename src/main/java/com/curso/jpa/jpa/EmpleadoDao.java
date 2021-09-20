package com.curso.jpa.jpa;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	       
	       
	       //Metodo para buscar por filtrado
	       try {
	    	   dao.buscarPorCriterios(0, 1);
			} catch (Exception e1) {
				System.out.println("Introduzca valores validos");
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
	
	/**/
	
	/**
	* Metodo que devuelve la lista de los empeados de un departamento o
	* de todos los departamentos (idDepartamentos llega null) si no se indica
	* cuyo salario es mayor que el indicado
	*
	* @param idDepartamento - opcional null
	* @param salarioMayorAEste no opcional
	* @return
	 * @throws Exception 
	*/
	public List<Empleado> buscarPorCriterios(int idDepartamento, double salarioMayorAEste ) throws Exception{
		
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Empleado> consulta = cb.createQuery(Empleado.class);
		
		Root<Empleado> personas = consulta.from(Empleado.class); //FROM Empleado e
		Predicate pDepartamento = null,pSalarioMayor = null; // where p1 p2
		
		if(idDepartamento != 0) {
			pDepartamento = cb.equal(personas.get("department_id"), idDepartamento);
		}
		
		if(salarioMayorAEste != 0) {
			
			pSalarioMayor = cb.equal(personas.get("salary"), salarioMayorAEste);
			
		}
		if(idDepartamento != 0 && salarioMayorAEste !=0) {
			
			Predicate departamentoAndSalario = cb.and(pDepartamento,pSalarioMayor);
			consulta.select(personas).where(departamentoAndSalario);
		}
		else if(idDepartamento != 0) {
			
			consulta.select(personas).where(pDepartamento);
		}
		else if(salarioMayorAEste != 0) {
			
			consulta.select(personas).where(pSalarioMayor);
			
		}if(salarioMayorAEste <= 0) {
			
			throw new Exception("Introduzca el salario");
			
		}
		
		List<Empleado> lista = em.createQuery(consulta).getResultList();
		System.out.println("Aqui empieza la busqueda por filtro");
		
		for(Empleado p: lista) {

			System.out.println(p.getFirst_name() + " - " + p.getDepartment_id()+" "+p.getSalary());
		}
	return lista;
	}

	/*Metodo para buscar empleados con comodines (like)*/
	/*PENDIENTE*/
	public List<Empleado> buscarPorCriteriaLike(String palabra){
		
		EntityManager em = factory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empleado> consulta = cb.createQuery(Empleado.class);
		
		Root<Empleado> personas = consulta.from(Empleado.class); //FROM Empleado e
		
		Predicate pNombre = cb.conjunction();// where p1 p2
		
		
		if(palabra != "") {
			//pNombre = cb.like(personas.get("first_name"), "%"+palabra+"%");
		}
		return null;
	}

}
