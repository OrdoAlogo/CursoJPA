package com.curso.jpa.domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEES")
//Named Queries
@NamedQueries({
	@NamedQuery(name="Empleado.finAll",query="SELECT e FROM Empleado e"),
	@NamedQuery(name="Empleado.findByTrabajoId",query="SELECT e FROM Empleado e WHERE e.job_id = :job_id")
	})
public class Empleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
	@SequenceGenerator(sequenceName = "EMPLOYEES_SEQ", name = "CUST_SEQ", allocationSize=1)
    @Column(name = "EMPLOYEE_ID")
    private Long id;
	
	@Column(name = "FIRST_NAME")
	private String first_name;
	
	@Column(name = "LAST_NAME")
	private String last_name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONE_NUMBER")
	private String phone_number;
	
	@Column(name = "HIRE_DATE")
	@Temporal(TemporalType.DATE)
	private Date hire_date;
	
	//USAR PARA INSERT Y UPDATE
		@Column(name = "JOB_ID")
		private String job_id; 
		//Aqui definimos la relacion OneToMany (Funciona)
//		@OneToOne
//		@JoinColumn(name = "JOB_ID")
//		private Trabajo trabajo;
		
		//PARA SELECT
		@ManyToOne(fetch = FetchType.EAGER)//Trae datos de forma inmediata, el LAZY trae datos hasta que se ejecuta la consulta
		@JoinColumn(name = "JOB_ID", insertable = false, updatable = false) 
		private Trabajo trabajo;
	
	@Column(name = "SALARY")
	private double salary;
	
	@Column(name = "COMMISSION_PCT")
	private double commission_pct;
	
	@Column(name = "MANAGER_ID")
	private double manager_id;
	
	@Column(name = "DEPARTMENT_ID")
	private double  department_id;
	
	public Empleado() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public Trabajo getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}
	public double getManager_id() {
		return manager_id;
	}
	public void setManager_id(double manager_id) {
		this.manager_id = manager_id;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void setCommission_pct(double commission_pct) {
		this.commission_pct = commission_pct;
	}
	public double getSalary() {
		return salary;
	}
	public double getCommission_pct() {
		return commission_pct;
	}
	public double getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(double department_id) {
		this.department_id = department_id;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	@Override
	public String toString() {
		return "EmpleadoDAO [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", phone_number=" + phone_number + ", hire_date=" + hire_date + ", job_id=" + trabajo + ", salary="
				+ salary + ", commission_pct=" + commission_pct + ", manager_id=" + manager_id + ", department_id="
				+ department_id + "]";
	}
	

	

}
