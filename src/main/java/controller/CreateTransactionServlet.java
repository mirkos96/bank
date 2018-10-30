package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.AccountManager;
import manager.PeriodicTransactionManager;
import model.Account;
import model.BadArgumentException;
import model.Category;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.TransactionType;

@WebServlet("/newTransaction")
public class CreateTransactionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB 
	private PeriodicTransactionManager periodicTransactionManager;
	
	@EJB
    private AccountManager accountManager;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setAttribute("currentAccount",accountManager.findById(Integer.valueOf(req.getParameter("account"))));
		req.setAttribute("targets",periodicTransactionManager.findAllTargets());
		req.setAttribute("categories",periodicTransactionManager.findAllCategories());
		req.setAttribute("transactionTypes",periodicTransactionManager.findAllTypes());
		getServletContext().getRequestDispatcher("/createTransaction.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		Account currentAccount=accountManager.findById(Integer.valueOf(req.getParameter("account")));
		
		try {
			String wording = req.getParameter("wording");
			String description=req.getParameter("description");
			Double transactionValue=periodicTransactionManager.getAmount(req.getParameter("rd-sign"), req.getParameter("amount")); //a remplacer avec la valeur rentrée
			Category category = periodicTransactionManager.findCatById(Integer.valueOf(req.getParameter("slct-category")));
			TransactionType transactionType = periodicTransactionManager.findTypById(Integer.valueOf(req.getParameter("slct-type")));
			TargetTransaction targetTransaction = periodicTransactionManager.findTarById(Integer.valueOf(req.getParameter("slct-target")));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dateOperation = sdf.parse(req.getParameter("date"));
			
			PeriodicTransaction newTransaction=  new PeriodicTransaction(wording, transactionValue, dateOperation, null,
					0, description, transactionType, targetTransaction, category, null);
			newTransaction.setAccount(currentAccount);
			periodicTransactionManager.saveTransaction(newTransaction);
			resp.sendRedirect(req.getContextPath()+"/transactionList?account="+currentAccount.getId());
		} 
		catch (BadArgumentException err){
			resp.setContentType("text/html");
			resp.getWriter().write("<!DOCTYPE html><script>ALERT("+err+")</script></html>");
		}
		catch (ParseException e) {			
		}		
		catch (NullPointerException err){
		} 
		
	}
}
