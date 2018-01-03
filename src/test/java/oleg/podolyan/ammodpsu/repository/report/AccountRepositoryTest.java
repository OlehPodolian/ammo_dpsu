package oleg.podolyan.ammodpsu.repository.report;

import oleg.podolyan.ammodpsu.domain.report.Account;
import oleg.podolyan.ammodpsu.domain.report.AccountItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AccountRepository accountRepository;

	private Account account;

	private AccountItem accountItem;

	@Before
	public void setUp() throws Exception {
		account = new Account("Account # 1");
		entityManager.persist(account);

		accountItem = new AccountItem("Fignya #1", 123.56, LocalDate.now());
		setAuditable(LocalDateTime.now(), "devil");
		entityManager.persist(accountItem);

		account.addAccountItem(accountItem);

		entityManager.flush();
	}

	@Test
	public void whenFindByName_thenReturnAccount() {
		Account found = accountRepository.findByName(account.getName());
		assertEquals(found.getName(), account.getName());
	}

	@Test(expected = NullPointerException.class)
	public void whenFindByNameFail_thenReturnNull() {
		Account found = accountRepository.findByName("Account # 3");
		assertNull(found);
		found.getName();
	}

	@Test
	public void whenGetAccountItems_thenReturnNotEmptyList() {
		Account found = accountRepository.findByName(account.getName());
		assertFalse(found.getAccountItems().isEmpty());
	}

	@Test
	public void whenFindAllByDeletedIsFalse_thenReturnEmptyList() {
		accountRepository.delete(account.getId());
		assertTrue(accountRepository.findAllByDeletedIsFalse().isEmpty());
	}

	@Test
	public void whenSoftDeleteAccount_thenSetItDeleted(){
		accountRepository.delete(account);
		assertFalse(accountRepository.findAll().isEmpty());
		Account found = accountRepository.findByName(account.getName());
		assertTrue(found.isDeleted());
	}

	private void setAuditable(LocalDateTime created, String user){
		accountItem.setCreated(created);
		accountItem.setLastModified(created);
		accountItem.setCreatedBy(user);
		accountItem.setLastModifiedBy(user);
	}
}