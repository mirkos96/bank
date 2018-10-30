package manager;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import model.AccountType;
import model.Address;
import model.Agency;
import model.Bank;
import model.CpCity;

public abstract class EjbContainerTest {

	private static EJBContainer container;
	@EJB
	private AgencyManager agencyManager;
	@Resource
	protected UserTransaction tx;
	
	@PersistenceContext(unitName="MyBankPersistence")
	protected EntityManager em;
	
	@BeforeClass
	public static void start() throws Exception {
		container = EJBContainer.createEJBContainer();
	}

	@AfterClass
	public static void shutdown() {
		if (container != null) {
			container.close();
		}
	}

	@Before
	public void inject() throws Exception {
		container.getContext().bind("inject", this);
		tx.begin();
		em.createNativeQuery("truncate schema PUBLIC and commit no check").executeUpdate();
		em.clear();
		tx.commit();
	}

	@After
	public void reset() throws Exception {
		container.getContext().unbind("inject");
	}

}
