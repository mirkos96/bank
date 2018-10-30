package controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.AccountManager;
import manager.PeriodicTransactionManager;
import model.Account;
import model.PeriodicTransaction;

@WebServlet("/transactionList")

public class PeriodicTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
     @EJB
     private PeriodicTransactionManager periodicTransactionManager;
    
     @EJB
     private AccountManager accountManager;

     
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		int id = Integer.valueOf(req.getParameter("account"));
		Account currentAccount=accountManager.findById(id);
		List <PeriodicTransaction> periodicTransactions = this.periodicTransactionManager.findAllByAccountId(id);
		req.setAttribute("periodicTransaction", periodicTransactions);
		req.setAttribute("currentAccount", currentAccount);
		req.getRequestDispatcher("/PeriodicTransaction.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		req.setAttribute("contextPath", getServletContext().getContextPath());
		periodicTransactionManager.DeleteTransaction(Integer.valueOf(req.getParameter("transaction")));
		resp.sendRedirect(getServletContext().getContextPath()+"/transactionList?account="+req.getParameter("accountId"));

	}
	
}
