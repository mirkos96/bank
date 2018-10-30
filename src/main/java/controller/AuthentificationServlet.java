package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.AuthentificationManager;
import model.Owner;
import model.ItemDoesNotExistException;


@WebServlet("/authentification")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private AuthentificationManager authentificationManager;
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/authentification.jsp").forward(req,resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("test");
		String inputpwd = req.getParameter("pwd");
		String inputlogin = req.getParameter("login");
			
			Owner owner = new Owner();
			try {
				/**
				 * List<Owner> owners = authentificationManager.findAll();
				 * for(Owner o : Owners){
				 * 		if(inputlogin.equals(o.getLogin)){
				 * 			owner = o;
				 * 		}
				 * 		else{
				 * 			resp.sendRedirect(errorjsp);
				 * 		}
				 * }
				 */
				owner = this.authentificationManager.findOwnerByLogin(inputlogin, inputpwd);
			} catch (ItemDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("login", owner);
			req.setAttribute("pwd", owner);
			System.out.println("success");
			resp.sendRedirect(getServletContext().getContextPath()+"/choiceAccountServlet");
		
		


	}

	

}
