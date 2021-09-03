package com.curso.jpa.estategiasmapeo;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name = "CUENTABANKARIA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_CUENTA")
public abstract class CuentaBancaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="NUM_CUENTA")
	private String num_cuenta;
	
	@Column(name="SALDO")
	private double saldo;

	@Column(name="TIPO_CUENTA")
	private String tipo;
	
	public CuentaBancaria() {
		super();
	}
	public CuentaBancaria(String num_cuenta, double saldo) {
		super();
		this.num_cuenta = num_cuenta;
		this.saldo = saldo;
		
	}
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNum_cuenta() {
		return num_cuenta;
	}
	public void setNum_cuenta(String num_cuenta) {
		this.num_cuenta = num_cuenta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((num_cuenta == null) ? 0 : num_cuenta.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaBancaria other = (CuentaBancaria) obj;
		if (num_cuenta == null) {
			if (other.num_cuenta != null)
				return false;
		} else if (!num_cuenta.equals(other.num_cuenta))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CuentaBancaria [num_cuenta=" + num_cuenta + ", saldo=" + saldo + "]";
	}
	
	

}
