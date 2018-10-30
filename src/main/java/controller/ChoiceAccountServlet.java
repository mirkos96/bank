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
import model.Account;

/**
 * Servlet implementation class ChoiceAccountServlet
 */
@WebServlet("/choiceAccountServlet")
public class ChoiceAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AccountManager accountManager;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Account> accounts = accountManager.findAll();
		request.setAttribute("accounts", accounts);
		request.setAttribute("contextPath", getServletContext().getContextPath());
		getServletContext().getRequestDispatcher("/choiceAccount.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	


}
