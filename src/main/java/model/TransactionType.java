package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="transactiontype")
@NamedQueries({
	@NamedQuery(name="TransactionType.findAll", query="SELECT t FROM TransactionType t"),
	@NamedQuery(name="TransactionType.findAllName", query="SELECT t.wording FROM TransactionType t")
})

public class TransactionType implements Serializable{
	private static final long serialVersionUID = 1L;

	/* VARIABLES */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="wording")
	private String wording;
	
	/* CONSTRUCTORS */
	public TransactionType(){
	}
	
	public TransactionType(String wording){
		
		if(wording.isEmpty()){
			throw new IllegalArgumentException("Transaction type can't be null or empty");
		}

		this.wording = wording;
	}
	
	/*GETTERS & SETTERS*/
	
	public Integer getId(){
		return this.id;
	}
	
	public String getWording(){
		return this.wording;
	}
	public void setWording(String wording){
		if(wording.isEmpty()){
			throw new IllegalArgumentException("Transaction type can't be null or empty");
		}
		this.wording = wording;
	}
	public void setId(Integer val){
		if(val <= 0){
			throw new IllegalArgumentException();
		}
		this.id = val;
	}
	
	@Override
	/**
	 * Return true si tout les champs sont �gaux (mais pas les addresses m�moires)
	 */
	public boolean equals(Object obj){
		if(obj instanceof TransactionType){
			TransactionType tmp = (TransactionType)obj;
		
			if(tmp.getWording().equals(this.getWording())){
				return true;
			}
			else return false;
		}
		else return false;	
	}
	
	@Override
	/**
	 * Return le type de la transaction;
	 */
	public String toString(){
		return this.wording;
	}
}
