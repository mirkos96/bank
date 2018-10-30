package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;

import model.ItemDoesNotExistException;
import model.Owner;

@Stateless
public class AuthentificationManager {
	
	@PersistenceContext(unitName="MyBankPersistence")
	private EntityManager em;
	
	
	public Owner findOwnerByLogin(String login, String pwd) throws ItemDoesNotExistException {
		
		String hashed = "";
		
		try {
			Query q =em.createQuery("select o from Owner o where o.login = :inputlogin", Owner.class);
			q.setParameter("inputlogin", login);
			Owner lg = (Owner)q.getSingleResult();
			System.out.println(lg);
			
			if(lg != null) {
				hashed = lg.getPwd();
				
					if(BCrypt.checkpw(pwd, hashed)) {
						System.out.print(String.format("Welcome to your personnal bank, %s!", lg.getFirstname()));
					}
					else {
						System.out.print("Credentials dot not match. Please try again.");
					}
			}
			else {
				System.out.print(" The login is incorrect ! Please try again! "); 
				}
			return lg;
		}
		catch (NoResultException e) {
			throw new ItemDoesNotExistException();
		}

	}
	
}

	

