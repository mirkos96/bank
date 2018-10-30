package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.AccountManager;
import manager.AccountTypeManager;
import manager.AgencyManager;
import model.Account;
import model.AccountType;
import model.Agency;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/newAccount")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private AgencyManager agencyManager;
	@EJB
	private AccountTypeManager accountTypeManager;
	@EJB
	private AccountManager accountManager;;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Agency> agencies = agencyManager.findAll();
		List<AccountType> accounttype = accountTypeManager.findAll(); 
		Account currentAccount = new Account();
		System.out.println(request.getParameter("accountId"));
		if(request.getParameter("accountId") != null){
			currentAccount = accountManager.findById(Integer.valueOf(request.getParameter("accountId")));
			request.setAttribute("currentAccount", accountManager.findById(Integer.valueOf(request.getParameter("accountId"))));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(currentAccount.getCreationDate());
			request.setAttribute("creationDate", date);
			
		}
		request.setAttribute("agencies", agencies);
		request.setAttribute("accounttype", accounttype);
		request.setAttribute("contextPath", getServletContext().getContextPath());
		getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
	}
}
