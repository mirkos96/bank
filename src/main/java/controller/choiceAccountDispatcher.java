package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.AccountManager;
import model.Account;

/**
 * Servlet implementation class choiceAccountDispatcher
 */
@WebServlet("/choiceAccount")
public class choiceAccountDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AccountManager accountManager;
    
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Account account = accountManager.findById(Integer.valueOf(req.getParameter("account")));
		req.setAttribute("account", account.getId());
		getServletContext().getRequestDispatcher("/transactionList").forward(req, resp);
	}
	

}
