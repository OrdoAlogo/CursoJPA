package com.curso.jpa.estategiasmapeo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PruebasEstrategiasMapeo {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("OracleHRPU");
	public static void main(String[] args) {
		PruebasEstrategiasMapeo pem = new PruebasEstrategiasMapeo();
		List<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();
		cuentas.add(new CuentaAhorro("0002",2200.2,0.2));
		cuentas.add(new CuentaAhorro("0003",5000.2,0.25));
		cuentas.add(new CuentaAhorro("0004",1500.5,0.2));
		cuentas.add(new CuentaCredito("0005",1550.5,3000.0));
		cuentas.add(new CuentaCredito("0006",2500.25,5000.0));
		cuentas.add(new CuentaCredito("0007",3050.5,3000.0));
		
		//FUNCIONA
//		for(int i = 0; i<cuentas.size(); i++) {
//			pem.añadirCuenta(cuentas.get(i));
//			System.out.println("Cuenta: "+cuentas.get(i).getNum_cuenta()+" añadida a la BD");
//		}
		
		List<CuentaBancaria> listaCuentas = pem.cargarCuentas();
		
		for(int i = 0; i<listaCuentas.size(); i++) {
			
			CuentaBancaria cuenta = listaCuentas.get(i);
			
			if(cuenta instanceof CuentaAhorro) {
				CuentaAhorro ahorro = (CuentaAhorro) cuenta;
				System.out.println("Cuenta de Ahorros");
				System.out.println("Nº Cuenta: "+ahorro.getNum_cuenta()+", Saldo: "+ahorro.getSaldo()+"€"+", Interes: "+ahorro.getInteres());
				System.out.println();
			}else {
				CuentaCredito credito = (CuentaCredito) cuenta;
				System.out.println("Cuenta de Crédito");
				System.out.println("Nº Cuenta: "+credito.getNum_cuenta()+", Saldo: "+credito.getSaldo()+"€"+", Limite: "+credito.getCredito()+"€");
				System.out.println();
			}
		}

	}
	
	//METODO PARA AGREGAR CUENTAS BANCARIAS A LA BASE DE DATOS
	public void añadirCuenta(CuentaBancaria cuenta) {
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(cuenta);
		tx.commit();
	}
	
	//METODO PARA CARGAR LAS CUENTAS BANCARIAS E IMPRIMIRLAS
	public List<CuentaBancaria> cargarCuentas() {
		
		EntityManager em = factory.createEntityManager();
		String consulta = "SELECT c FROM CuentaBancaria c";
		Query query = em.createQuery(consulta);
		List<CuentaBancaria> cuentas = query.getResultList();
		
		return cuentas;
		
	}

}
