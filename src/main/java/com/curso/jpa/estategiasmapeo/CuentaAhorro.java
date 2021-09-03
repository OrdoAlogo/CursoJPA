package com.curso.jpa.estategiasmapeo;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "CA")

public class CuentaAhorro extends CuentaBancaria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="INTERES")
	private double interes;
	
	public CuentaAhorro() {
		super();
	}

	public CuentaAhorro(String num_cuenta, double saldo, double interes) {
		super(num_cuenta,saldo);
		this.interes = interes;
	}

	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}

	@Override
	public String toString() {
		return "CuentaAhorro [interes=" + interes + "]";
	}
	
	

	
}
