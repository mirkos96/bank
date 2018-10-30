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
import model.ItemDoesNotExistException;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.TransactionType;

@WebServlet("/editTransaction")
public class EditTransactionServlet extends HttpServlet {

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
		PeriodicTransaction currentTransaction;
		try {
			currentTransaction = periodicTransactionManager.findById(Integer.valueOf(req.getParameter("transaction")));
			req.setAttribute("currentTransaction",currentTransaction);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(currentTransaction.getDateOperation());
			String rdsign=periodicTransactionManager.getSign(currentTransaction.getTransactionValue());
			req.setAttribute("date",date);
			req.setAttribute("rdsign",rdsign);
			req.setAttribute("amount", Math.abs(currentTransaction.getTransactionValue()));
			req.setAttribute("targets",periodicTransactionManager.findAllTargets());
			req.setAttribute("categories",periodicTransactionManager.findAllCategories());
			req.setAttribute("transactionTypes",periodicTransactionManager.findAllTypes());
			getServletContext().getRequestDispatcher("/createTransaction.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int idTransaction=Integer.valueOf(req.getParameter("id"));
		int idAccount=Integer.valueOf(req.getParameter("account"));
		
		String wording = req.getParameter("wording");
		String description=req.getParameter("description");
		Category category = periodicTransactionManager.findCatById(Integer.valueOf(req.getParameter("slct-category")));
		TransactionType transactionType = periodicTransactionManager.findTypById(Integer.valueOf(req.getParameter("slct-type")));
		TargetTransaction targetTransaction = periodicTransactionManager.findTarById(Integer.valueOf(req.getParameter("slct-target")));
		try {
			Double transactionValue=periodicTransactionManager.getAmount(req.getParameter("rd-sign"), req.getParameter("amount")); //a remplacer avec la valeur rentrée
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dateOperation = sdf.parse(req.getParameter("date"));
			
			periodicTransactionManager.editTransaction(wording, transactionValue, dateOperation, null,
					0, description, transactionType, targetTransaction, category, null,idTransaction);
		} 
		catch (ParseException e) {			
		}
		catch (BadArgumentException e){}
		resp.sendRedirect(req.getContextPath()+"/transactionList?account="+idAccount);
	}
}

