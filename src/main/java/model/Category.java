package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Category")
@NamedQueries({
	@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c"),
	@NamedQuery(name="Category.findAllName", query="SELECT c.wording FROM Category c")
}) 


public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "wording", nullable = false)
	private String wording;
	
	@ManyToOne
	@JoinColumn(name="id_subCategory")
	private Category category;

	public Category(String wording,Category category) {
	
		if (wording.isEmpty()){
			throw new IllegalArgumentException("wording cannot be empty");
		}
		this.wording=wording;
		this.category=category; 
	}
	public Category() {
		
	}
	
	public Integer getId() { 
		return this.id;
	}
	public String getWording(){
		return this.wording;
	}
	public void setWording(String wording){
		if (wording.isEmpty()){
			throw new IllegalArgumentException("wording cannot be empty");
		}
		this.wording = wording;
	}
	
	public Category getCategory(){
		return this.category;
	}
	public void setCategory(Category cat){
		this.category = cat;
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
		if(obj instanceof Category){
			Category tmp = (Category)obj;
			if(tmp.getWording() == null){
				tmp.setWording("");
			}
			if(tmp.getWording().equals(this.getWording())){
				if(tmp.getCategory() != null){
					return tmp.getCategory().equals(this.getCategory());
				}
				else {
					return (tmp.getCategory() == null && this.getCategory() == null);
				}
			}
			else return false;
		}
		else return false;
	}
	
	@Override
	/**
	 * Return le nom de la cat�gorie
	 */
	public String toString(){
		return this.wording;
	}
	
	
}
