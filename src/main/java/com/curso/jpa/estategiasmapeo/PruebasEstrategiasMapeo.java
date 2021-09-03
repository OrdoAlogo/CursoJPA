package com.curso.jpa.estategiasmapeo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PruebasEstrategiasMapeo {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("OracleHRPU");
	public static void main(String[] args) {
		PruebasEstrategiasMapeo pem = new PruebasEstrategiasMapeo();
		
		CuentaBancaria ahorro1 = new CuentaAhorro("0001",1200.2,0.00);
		CuentaBancaria ahorro2 = new CuentaAhorro("0002",2200.2,0.00);
		CuentaBancaria corriente1 = new CuentaCredito("0003",5000.2,0.00);
		
		pem.añadirCuenta(ahorro1);
		pem.añadirCuenta(ahorro2);
		pem.añadirCuenta(corriente1);

	}
	
	public void añadirCuenta(CuentaBancaria cuenta) {
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(cuenta);
		tx.commit();
	}

}
