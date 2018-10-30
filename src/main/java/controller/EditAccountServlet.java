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
import manager.AccountTypeManager;
import manager.AgencyManager;
import manager.CountryCodeManager;
import model.Account;
import model.AccountType;
import model.Agency;
import model.CountryCode;

/**
 * Servlet implementation class EditAccountServlet
 */
@WebServlet("/EditAccount")
public class EditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AccountManager accountManager;
	@EJB
	private AgencyManager agencyManager;
	@EJB
	private AccountTypeManager accountTypeManager;
	@EJB
	private CountryCodeManager countryCodeManager;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				Agency agency = agencyManager.findById(Integer.valueOf(request.getParameter("agency")));
				AccountType accounttype = accountTypeManager.findById(Integer.valueOf(request.getParameter("accounttype")));
				CountryCode cc = countryCodeManager.findById(1);
				String accountNumber = request.getParameter("accountnumber");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date creationDate = new Date();
				try {
					creationDate = sdf.parse(request.getParameter("creationdate"));	
				} catch (ParseException e) {
					System.err.print("Failed to parse DATE object. Account has been created with today date");
				}
				double solde = Double.valueOf(request.getParameter("balance"));
				double interest = Double.valueOf(request.getParameter("interestrate"));
				int alert = Integer.valueOf(request.getParameter("alert"));
				int overdraft = Integer.valueOf(request.getParameter("overdraft"));
				int id = Integer.valueOf(request.getParameter("id"));
				
				Account newAccount = new Account(accountNumber, creationDate, solde, overdraft, interest, agency, cc, accounttype, alert);
				try{
					accountManager.edit(newAccount, id);
					response.sendRedirect(getServletContext().getContextPath()+"/transactionList?account="+id);
					
				}catch(Exception e){
					
				}
	}

}
