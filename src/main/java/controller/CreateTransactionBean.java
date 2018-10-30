package controller;


import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import manager.PeriodicTransactionManager;
import model.Category;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.TransactionType;

@Named("newTransaction")
@RequestScoped
public class CreateTransactionBean {
	
	@EJB
	private PeriodicTransactionManager periodicTransactionManager;
	
	public PeriodicTransaction getPeriodicTransaction(){
		PeriodicTransaction periodicTransaction=new PeriodicTransaction();
		return periodicTransaction;
	}
	public List<TargetTransaction> getTargets(){
		return periodicTransactionManager.findAllTargets();
	}
	
	public List<Category> getCategories(){
		return periodicTransactionManager.findAllCategories();
	}
	
	public List<TransactionType> getTypes(){
		return periodicTransactionManager.findAllTypes();
	}
	
	public String getAmountError(){
		return "le montant doit être rempli !";
	}
	
}
