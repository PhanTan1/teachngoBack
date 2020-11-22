package be.teachngo.repository;

import be.teachngo.data.AccountStatus;
import be.teachngo.data.Administrator;
import be.teachngo.data.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static be.teachngo.repository.AdministratorRepositoryIntegrationTest.getAdministrator;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @Autowired

    private AdministratorRepository administratorRepository;

    @Autowired
    private PayPalAccountRepository payPalAccountRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired

    private AccountRepository accountRepository;

    @Test
    public void testAllAccount() {
        administratorRepository.deleteAll();
        Administrator admin = getAdministrator();
        admin = administratorRepository.save(admin);
        BankAccount account2 = new BankAccount();
        account2.setNumber("456461515151");
        account2.setIban("BE7185534454225");
        account2.setBic("BRUING");
        account2.setCreationDate(new Date());
        account2.setStatus(AccountStatus.CREATED);
        account2.setUser(admin);
        bankAccountRepository.save(account2);
        List<BankAccount> allBankAccount = bankAccountRepository.findAll();
        assertEquals(1, allBankAccount.size());

/**
 PayPalAccount account1 = new PayPalAccount();
 account1.setCreationDate(new Date());
 account1.setStatus(AccountStatus.CREATED);
 account1.setEmail("test@gmail.com");
 payPalAccountRepository.save(account1);
 List<PayPalAccount> allAccount = payPalAccountRepository.findAll();
 assertEquals(1, allAccount.size());

 List<Account> allAccounts = accountRepository.findAll();
 assertEquals(2, allAccounts.size());
 **/

    }


}