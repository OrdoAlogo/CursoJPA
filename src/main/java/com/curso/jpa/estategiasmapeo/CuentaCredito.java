package com.curso.jpa.estategiasmapeo;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "CC")
public class CuentaCredito extends CuentaBancaria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="CREDITO")
	private double credito;

	public CuentaCredito() {
		super();
	}
	
	
	public CuentaCredito(String num_cuenta, double saldo,double credito) {
		super(num_cuenta,saldo);
		this.credito = credito;
	}


	public double getCredito() {
		return credito;
	}
	public void setCredito(double credito) {
		this.credito = credito;
	}
	
	@Override
	public String toString() {
		return "CuentaCredito [credito=" + credito + "]";
	}
	
	

}
