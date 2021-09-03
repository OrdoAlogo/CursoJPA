package com.curso.jpa.jpa;

import java.util.List;
import javax.persistence.*;
import com.curso.jpa.domain.Employee;
import com.curso.jpa.domain.Department;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//POR CODIGO
		//1._ Creo una factoria de Entiti Manager en el create (nombre de la unidad de persistencia)
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("miUnidad");
		EntityManager em = factory.createEntityManager();
		JpaTest test = new JpaTest(em);

		EntityTransaction tx = em.getTransaction();
		tx.begin();//INICIAMOS LA TRANSACCION
		//OPERACIONES CRUD
			//em.persist();---->HACE UN INSERT
//				Department dp = new Department();
//				dp.setName("DESARROLLO");
//				em.persist(dp);
		
		   //em.find ------>Buscar por id
				long id = 1;
				Department d1 = em.find(Department.class, id);
			System.out.println("El departamendo 1: "+d1.getName());
				
			//em.set()----->Modifica
				//d1.setName("Departamento de Desarrollo");
				
			//em.remove()------->Borra
				em.remove(d1);
//		try {
//			test.createEmployees();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		tx.commit();//CONFIRMA y lanza los cambios a la bbdd
		//tx.rollback(); //Deshace los cambios y las ordenes no van a la bd
		test.listEmployees();

		System.out.println(".. done");
	}




	private void createEmployees() {
		int numOfEmployees = manager.createQuery("Select a From Employee a", Employee.class).getResultList().size();
		if (numOfEmployees == 0) {
			Department department = new Department("java");
			manager.persist(department);

			manager.persist(new Employee("Jakab Gipsz",department));
			manager.persist(new Employee("Captain Nemo",department));

		}
	}


	private void listEmployees() {
		List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
		System.out.println("num of employess:" + resultList.size());
		for (Employee next : resultList) {
			System.out.println("next employee: " + next);
		}
	}


}
