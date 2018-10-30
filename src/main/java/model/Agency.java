package model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="agency")
@NamedQuery(name="Agency.findAll", query="SELECT ag FROM Agency ag")
public class Agency {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="agency_name")
	private String agency_name;
	@Column(name="counter_code")
	private String counter_code;
	@ManyToOne
	@JoinColumn(name="id_address")
	private Address address;
	@ManyToOne
	@JoinColumn(name="id_bank")
	private Bank bank;
	
	/**
	 * 
	 * @param agency_name : 
	 * @param counter_code : 
	 * @param address : 
	 * @param bank : 
	 */

	public Agency(String agency_name, String counter_code, Address address, Bank bank) {
		
		if(agency_name.isEmpty()) {
			throw new IllegalArgumentException("The agency name cannot be empty");
		}
		if(counter_code.length() != 5) {
			throw new IllegalArgumentException("The counter code must cointain five caracters");
		}
		if(address == null) {
			throw new NullPointerException("The agency address  cannot be null");
		}
		if(bank == null) {
			throw new NullPointerException("The bank agency cannot be null");
		}
		
	this.agency_name = agency_name;
	this.counter_code = counter_code;
	this.address = address;
	this.bank = bank;
	}
	
	public Agency(){
		
	}
	
	public Integer getId() {
		return this.id;
	} 
	
	public void setId(Integer val){
		/*if(val <= 0){
			throw new IllegalArgumentException();
		}*/
		this.id = val;
	}
	
	public String getAgencyName() {
		return this.agency_name;
	}
	public void setAgencyName(String name){
		/*if(name.isEmpty()) {
			throw new IllegalArgumentException("The agency name cannot be empty");
		}*/
		this.agency_name = name;
	}
	
	
	public String getCounterCode() {
		return this.counter_code;
	}
	
	public void setCounterCode(String code){
		/*if(code.length() != 5) {
			throw new IllegalArgumentException("The counter code must cointain five caracters");
		}*/
		this.counter_code = code;
	}
	
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(Address add){
		/*if(add == null) {
			throw new NullPointerException("The agency address  cannot be null");
		}*/
		this.address = add;
	}

	public Bank getBank() {
		return this.bank;
	}
	public void setBank(Bank bank){
		/*if(bank == null) {
			throw new NullPointerException("The bank cannot be null");
		}*/
		this.bank = bank;
	}
	@Override 
	/**
	 * Return true si tout les champs sont �gaux (mais pas les addresses m�moires)
	 */
	public boolean equals(Object obj){
		if(obj instanceof Agency){
			Agency tmp = (Agency)obj;
			if(((tmp.getAgencyName()==null ||this.getAgencyName()==null) || tmp.getAgencyName().equals(this.getAgencyName()))
				&& 
				((tmp.getCounterCode()==null || this.getCounterCode()==null) || tmp.getCounterCode().equals(this.getCounterCode()))
				&&	 
				((tmp.getAddress()==null || this.getAddress()==null) || tmp.getAddress().equals(this.getAddress()))
				&&
				((tmp.getBank()==null || this.getBank()==null) || tmp.getBank().equals(this.getBank()))
			){
				return true;
			}
			else return false;
		}
		else {
			return false;
		}
	}
	
	@Override
	/**
	 * Return le nom de l'agence
	 */
	public String toString() {
		return this.agency_name;
	}
}
