package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bank.BankI;
import clients.BankClient;
import model.BankDBImpl;

public class BankImplTest {
	final static long ACCOUNT = 00000000000001L;
	final static String NAME = "John Doe";
	final static float BALANCE = 1000;
	
	final static float AMOUNT = 100;
	
	final static Long ACCOUNT2 = 00000000000002L;
	final static String NAME2 = "Jane Doe";
	final static float BALANCE2 = 3000;

	final static Long FAKE_ACCOUNT = 33333333333L;

	@Before
	public void setUp() throws Exception {
		BankI dao = BankDBImpl.getInstance();
		dao.deleteAll();
		dao.crearCliente(ACCOUNT, NAME, BALANCE);

	}

	@After
	public void tearDown() throws Exception {
//		BankI dao = BankDBImpl.getInstance();
//		dao.deleteAll();
	}
	@Test
	public void testCrearCliente() {
		BankI dao = BankDBImpl.getInstance();
		BankClient bc = dao.crearCliente(ACCOUNT2, NAME2, BALANCE2);
		assertEquals( NAME2, bc.getName());
		assertEquals( BALANCE2, bc.getBalance(),0);
		assertEquals( ACCOUNT2, bc.getId());
	}

	@Test
	public void testConsultarSaldo() {
		BankI dao = BankDBImpl.getInstance();
		BankClient bc = dao.consultarSaldo(ACCOUNT);
		assertEquals(BALANCE, bc.getBalance(), 0);
		assertNull(dao.consultarSaldo(FAKE_ACCOUNT));
		
	}

	@Test
	public void testIngresar() {
		BankI dao = BankDBImpl.getInstance();
		BankClient bc = dao.ingresar(ACCOUNT, AMOUNT);
		assertEquals(BALANCE + AMOUNT , bc.getBalance(), 0);
		assertEquals(BALANCE + AMOUNT , dao.consultarSaldo(ACCOUNT).getBalance(), 0);
		assertNull(dao.ingresar(FAKE_ACCOUNT, AMOUNT));

	}

	@Test
	public void testRetirar() {
		BankI dao = BankDBImpl.getInstance();
		BankClient bc = dao.retirar(ACCOUNT, AMOUNT);
		assertEquals(BALANCE - AMOUNT , bc.getBalance(), 0);
		assertEquals(BALANCE - AMOUNT , dao.consultarSaldo(ACCOUNT).getBalance(), 0);
		assertNull(dao.retirar(FAKE_ACCOUNT, AMOUNT));

	}



	@Test
	public void testTransferir() {
		BankI dao = BankDBImpl.getInstance();
		dao.crearCliente(ACCOUNT2, NAME2, BALANCE2);

		List<BankClient> list = dao.transferir(ACCOUNT2, ACCOUNT, AMOUNT);
		assertEquals(BALANCE2 - AMOUNT, list.get(0).getBalance(), 0);
		assertEquals(BALANCE + AMOUNT, list.get(1).getBalance(), 0);
		assertEquals(BALANCE2 - AMOUNT, dao.consultarSaldo(ACCOUNT2).getBalance(), 0);
		assertEquals(BALANCE + AMOUNT, dao.consultarSaldo(ACCOUNT).getBalance(), 0);
		assertEquals(new ArrayList<BankClient>(), dao.transferir(FAKE_ACCOUNT, ACCOUNT, AMOUNT));
		assertEquals(new ArrayList<BankClient>(), dao.transferir(ACCOUNT, FAKE_ACCOUNT, AMOUNT));
		assertEquals(new ArrayList<BankClient>(), dao.transferir(FAKE_ACCOUNT, FAKE_ACCOUNT, AMOUNT));
		assertEquals(new ArrayList<BankClient>(), dao.transferir(FAKE_ACCOUNT, FAKE_ACCOUNT, AMOUNT));
		assertEquals(BALANCE + AMOUNT, dao.consultarSaldo(ACCOUNT).getBalance(), 0);


	}

	
	@Test
	public void testBorrar() {
		BankI dao = BankDBImpl.getInstance();
		dao.borrar(ACCOUNT);
		assertNull(dao.borrar(FAKE_ACCOUNT));
		assertNull(dao.consultarSaldo(ACCOUNT));
		assertNull(dao.consultarSaldo(FAKE_ACCOUNT));
	}
	
	
	@Test
	public void testPopulateAndList() {
		BankDBImpl dao = BankDBImpl.getInstance();
		List<BankClient> all = new ArrayList<>();
		BankClient bc1 = new BankClient(ACCOUNT + 10, NAME, AMOUNT + 10);
		BankClient bc2 = new BankClient(ACCOUNT + 11, NAME, AMOUNT + 11);
		BankClient bc3 = new BankClient(ACCOUNT + 12, NAME, AMOUNT + 12);
		all.add(bc1);
		all.add(bc2);
		all.add(bc3);
		dao.populate(all);
		
		List<BankClient> retrieved = dao.lista();
		assertEquals(3, retrieved.size());
		assertEquals(bc1, retrieved.get(0));
		assertEquals(bc2, retrieved.get(1));
		assertEquals(bc3, retrieved.get(2));

	}

	@Test
	public void testDeleteAll() {
		BankI dao = BankDBImpl.getInstance();
		dao.deleteAll();	
		assertEquals(0, dao.lista().size());
	}


	

}
