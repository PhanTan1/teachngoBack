package be.teachngo.repository;

import be.teachngo.data.Account;
import be.teachngo.data.Administrator;
import be.teachngo.data.BankAccount;
import be.teachngo.data.PayPalAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @Test
    public void testAllAccount() {
        BankAccount account2 = new BankAccount();
        account2.setNumber("456461515151");
        account2.setIban("855225");
        account2.setBic("46465456");
        account2.setCreationDate(new Date());
        account2.setStatus("test");
        bankAccountRepository.save(account2);
        List<BankAccount> allBankAccount = bankAccountRepository.findAll();
        assertEquals(1, allBankAccount.size());


        PayPalAccount account1 = new PayPalAccount();
        account1.setCreationDate(new Date());
        account1.setStatus("hold");
        account1.setEmail("test@gmail.com");
        payPalAccountRepository.save(account1);
        List<PayPalAccount> allAccount = payPalAccountRepository.findAll();
        assertEquals(1, allAccount.size());

        List<Account> allAccounts = accountRepository.findAll();
        assertEquals(2, allAccounts.size());

    }

    @Autowired
    private PayPalAccountRepository payPalAccountRepository;

    @Autowired
    private  BankAccountRepository bankAccountRepository;

    @Autowired

    private AccountRepository accountRepository;

}