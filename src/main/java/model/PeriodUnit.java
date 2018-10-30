package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="periodunit")
@NamedQuery(name="PeriodUnit.findAll", query="SELECT a FROM PeriodUnit a")

public class PeriodUnit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="unit")
	private String unit;
	
	public PeriodUnit(){
	}

	public PeriodUnit(String unit) {
		
		if (unit.isEmpty()){
			throw new IllegalArgumentException("unit cannot be empty, eg 'day' or 'month'");
		}
		
		this.unit=unit;
	}
	
	
	public int getId(){
		return this.id;
	}
	public void setId(int val){
		if(val <= 0){
			throw new IllegalArgumentException();
		}
		this.id = val;
	}
	
	public String getUnit(){
		return this.unit;
	}
	public void setUnit(String unit){
		this.unit = unit;
	}
	@Override
	/**
	 * Return true si tout les champs sont �gaux (mais pas les addresses m�moires)
	 */
	public boolean equals(Object obj){
		if(obj instanceof PeriodUnit){
			PeriodUnit tmp = (PeriodUnit)obj;
		
			if(tmp.getUnit().equals(this.getUnit())){
				return true;
			}
			else return false;
		}
		else return false;	
	}
	
	@Override
	/**
	 * Return l'unit� de p�riodicit� (mensuelle, hebdomadaire, trimestrielle...)
	 */
	public String toString(){
		return this.unit;
	}
	
}
