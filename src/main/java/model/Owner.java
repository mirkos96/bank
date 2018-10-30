package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tools.Tools;

@Entity
@Table(name="Owner")
@NamedQuery(name="Owner.findAll", query = "SELECT o FROM Owner o")

public class Owner{
	
	@Column(name="birthdate")
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@Column(name="login")
	private String login;
	
	@Column(name="pwd")
	private String pwd;
	
	@ManyToOne
	@JoinColumn(name="id_address")
	private Address address;
	@Column(name="name")
	private String name;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="phonenumber")
	private String phonenumber;
	
	@Column(name="mail")
	private String mail;
	
	@Column(name="date_assignment")
	@Temporal(TemporalType.DATE)
	private Date date_assignment;
	
	/**
	 * Constructor
	 * @param name : The owner name
	 * @param firstname : 
	 * @param phonenumber : 
	 * @param mail : 
	 * @param birthdate : 
	 * @param login : 
	 * @param pwd : 
	 * @param address : 
	 */
	public Owner(String name, String firstname, String phonenumber, String mail, Date birthdate, String login, String pwd, Address address) {
		if(name.isEmpty()) {
			throw new IllegalArgumentException("The name cannot be empty");
		}
		if(firstname.isEmpty()) {
			throw new IllegalArgumentException("The firstname cannot be empty");
		}
		if(Tools.eraseChar(phonenumber,"\\s").length()<4||Tools.eraseChar(phonenumber,"\\s").length()>11) {
			throw new IllegalArgumentException("phonenumber must contain between 4 and 11 numbers");
		}
		if(!Tools.checkMail(mail)){
			throw new IllegalArgumentException("mail must be of a valid format eg toto@titi.tutu");
		}
		if (birthdate == null){
			throw new NullPointerException ("birthdate cannot be null");
		}
		if (birthdate.getTime()>Tools.today().getTime()){
			throw new IllegalArgumentException ("birthdate in the future");
		}
		if (login.isEmpty()){
			throw new IllegalArgumentException ("login cannot be empty");
		}
		if (pwd == null){
			pwd = "";
		}
		if (address == null){
			throw new NullPointerException ("address cannot be null");
		}
		this.birthdate=birthdate;
		this.login=login;
		this.pwd=pwd;
		this.address=address;
			
	}
	public Owner() {
		super();
	}
	
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date date){
		if (date == null){
			throw new NullPointerException ("birthdate cannot be null");
		}
		if (date.getTime()>Tools.today().getTime()){
			throw new IllegalArgumentException ("birthdate in the future");
		}
		this.birthdate = date;
	}
	
	public String getLogin() {
		return this.login;
	}
	public void setLogin(String login){
		if (login.isEmpty()){
			throw new IllegalArgumentException ("login cannot be empty");
		}
		this.login = login;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	public void setPwd(String pwd){
		if (pwd == null){
			pwd = "";
		}
		this.pwd = pwd;
	}
	
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(Address address){
		if (address == null){
			throw new NullPointerException ("address cannot be null");
		}
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.isEmpty()) {
			throw new IllegalArgumentException("The name cannot be empty");
		}
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		if(firstname.isEmpty()) {
			throw new IllegalArgumentException("The firstname cannot be empty");
		}
		this.firstname = firstname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		if(Tools.eraseChar(phonenumber,"\\s").length()<4||Tools.eraseChar(phonenumber,"\\s").length()>11) {
			throw new IllegalArgumentException("phonenumber must contain between 4 and 11 numbers");
		}
		this.phonenumber = phonenumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		if(!Tools.checkMail(mail)){
			throw new IllegalArgumentException("mail must be of a valid format eg toto@titi.tutu");
		}
		this.mail = mail;
	}
	@Override
	/**
	 * Return true si tout les champs sont �gaux (mais pas les addresses m�moires)
	 */
	public boolean equals(Object obj){
		if(obj instanceof Owner){
			Owner tmp = (Owner)obj;
		
			if(((tmp.getName()==null || this.getName()==null) || tmp.getName().equals(this.getName()))
				&& 
				((tmp.getFirstname()==null || this.getFirstname()==null) || tmp.getFirstname().equals(this.getFirstname())) 
				&&	 
				((tmp.getPhonenumber()==null||this.getPhonenumber()==null) || tmp.getPhonenumber().equals(this.getPhonenumber()))
				&&
				((tmp.getMail()==null || this.getMail()==null) || tmp.getMail().equals(this.getMail()))
				&&
				((tmp.getBirthdate()==null||this.getBirthdate()==null) || tmp.getBirthdate().equals(this.getBirthdate()))
				&&
				((tmp.getLogin()==null||this.getLogin()==null) || tmp.getLogin().equals(this.getLogin()))
				&&
				((tmp.getPwd()==null||this.getPwd()==null) || tmp.getPwd().equals(this.getPwd()))
				&&
				((tmp.getAddress()==null||this.getAddress()==null) || tmp.getAddress().equals(this.getAddress())))
			{
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	@Override
	/**
	 * Return le nom et le pr�nom
	 */
	public String toString() {
		return String.format("%s %s", getName(), this.getFirstname());
	}

}
