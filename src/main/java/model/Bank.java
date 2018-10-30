package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity  // pour dire que �a va aller dans la BDD
@Table(name="Bank")  // � quelle table c'est associ�
@NamedQuery(name="Bank.findAll", query = "SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;
	/*VARIABLES*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="bank_name")
	private String bank_name;
	@Column(name="bank_code")
	private String bank_code;
	

/**
 * 
 * @param bank_name :  
 * @param bank_code : 
 */

	public Bank(String bank_name, String bank_code) {
		if(bank_name.isEmpty()) {
			throw new IllegalArgumentException("The bank name cannot be empty");
		}
		if(bank_code.isEmpty()) {
			throw new IllegalArgumentException ("The bank code canoot be empty");
		}
		this.bank_name = bank_name;
		this.bank_code = bank_code;
		
	}
	public Bank() {
		
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer val){
		/*if(val <=0){
			throw new IllegalArgumentException("Id must be strictly superior to 0");
		}*/
		this.id = val;
	}
	
	public String getBankName() {
		return this.bank_name;
	}
	public void setBankName(String name){
		/*if(name.isEmpty()) {
			throw new IllegalArgumentException("The bank name cannot be empty");
		}*/
		this.bank_name = name;
	}
	
	public String getBankCode() {
		return this.bank_code;
	}
	public void setBankCode(String code){
		/*if(code.isEmpty()) {
			throw new IllegalArgumentException ("The bank code cannot be empty");
		}*/
		this.bank_code = code;
	}
	
	@Override
	/**
	 * Return true si tout les champs sont �gaux (mais pas les addresses m�moires)
	 */
	public boolean equals(Object obj){
		if(obj instanceof Bank){
			Bank tmp = (Bank)obj;
			if(((tmp.getBankName()==null || this.getBankName()==null) || tmp.getBankName().equals(this.getBankName())) 
				&& 
			   ((tmp.getBankCode()==null || this.getBankCode()==null) || tmp.getBankCode().equals(this.getBankCode())))
			{
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
	 * Return : ne nom de la banque
	 */
	public String toString() {
		return this.bank_name;
	}


}
