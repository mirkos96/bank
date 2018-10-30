package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tools.Tools;

@Entity
@Table(name="advisor")
@NamedQuery(name="Advisor.findAll", query="SELECT ad FROM Advisor ad")
public class Advisor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
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
	
	@ManyToOne
	@JoinColumn(name="id_agency")
	private Agency agency;
	
	/**
	 * 
	 * @param name : 
	 * @param firstname : 
	 * @param phonenumber : 
	 * @param mail : 
	 * @param date_assignment : 
	 * @param agency : 
	 */

	public Advisor(String name, String firstname, String phonenumber, String mail, Date date_assignment,Agency agency) {
		
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
		
		if(date_assignment == null) {
			throw new NullPointerException("Date assignment cannot be null");
		}
		if(date_assignment.getTime() > Tools.today().getTime()) {  
			throw new IllegalArgumentException ("Date assigment in the future");
		}
		if(agency == null) {
			throw new NullPointerException("Agency cannot be null");
		}
	
		this.name = name;
		this.firstname = firstname;
		this.phonenumber = phonenumber;
		this.mail = mail;
		this.date_assignment = date_assignment;
		this.agency = agency;
	
	}
	
	public Advisor(){
	}
	
	public Date getDateAssignment() {
		return this.date_assignment;
	}
	
	public void setDateAssignment(Date date){
		/*if(date == null) {
			throw new NullPointerException("Date assignment cannot be null");
		}
		if(date.getTime() > Tools.today().getTime()) {  
			throw new IllegalArgumentException ("Date assigment in the future");
		}*/
		this.date_assignment = date;
	}
	
	
	public Agency getAgency() {
		return this.agency;
	}
	public void setAgency(Agency agency){
		/*if(agency == null) {
			throw new NullPointerException("Agency cannot be null");
		}*/
		this.agency = agency;
	}
	
	//@Override
	/**
	 * Return true si tout les champs sont �gaux (mais pas les addresses m�moires)
	 */
	/*public boolean equals(Object obj){
		if(obj instanceof Advisor){
			Advisor tmp = (Advisor)obj;
		
			if(((tmp.getName()==null || this.getName()==null) || tmp.getName().equals(this.getName()))
				&& 
				((tmp.getFirstName()==null || this.getFirstName()==null) || tmp.getFirstName().equals(this.getFirstName())) 
				&&	 
				((tmp.getPhoneNumber()==null||this.getPhoneNumber()==null) || tmp.getPhoneNumber().equals(this.getPhoneNumber()))
				&&
				((tmp.getMail()==null || this.getMail()==null) || tmp.getMail().equals(this.getMail()))
				&&
				((tmp.getDateAssignment()==null||this.getDateAssignment()==null) || tmp.getDateAssignment().equals(this.getDateAssignment()))
				&&
				((tmp.getAgency()==null||this.getAgency()==null) || tmp.getAgency().equals(this.getAgency())))
			{
				return true;
			}
			else return false;
		}
		else return false;
	}*/
	
	@Override
	/**
	 * Return : Une cha�ne form�e du nom et du pr�nom
	 */
	public String toString() {
		return String.format("%s %s", this.name, this.firstname);
	}
	
}
