package be.teachngo.service.impl;

import be.teachngo.data.*;
import be.teachngo.repository.AccountRepository;
import be.teachngo.repository.BankAccountRepository;
import be.teachngo.repository.PayPalAccountRepository;
import be.teachngo.repository.UserRepository;
import be.teachngo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private PayPalAccountRepository payPalAccountRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public BankAccount save(BankAccount bankAccount, User user) {
        User one = userRepository.getOne(user.getId());
        if (one instanceof Student) {
            throw new IllegalArgumentException("A student can't have a bank account");
        }
        bankAccount.setUser(one);
        if (bankAccount.getId() == null) {
            // a new account
            bankAccount.setCreationDate(new Date());
            bankAccount.setStatus(AccountStatus.CREATED);
        } else {
            bankAccount.setUpdateDate(new Date());
        }
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public PayPalAccount save(PayPalAccount payPalAccount, User user) {

        User one = userRepository.getOne(user.getId());
        if (one instanceof Administrator) {
            throw new IllegalArgumentException("An Administrator can't have a paypal account");
        }
        payPalAccount.setUser(one);
        if (payPalAccount.getId() == null) {
            // a new account
            payPalAccount.setCreationDate(new Date());
            payPalAccount.setStatus(AccountStatus.CREATED);
        } else {
            payPalAccount.setUpdateDate(new Date());
        }
        return payPalAccountRepository.save(payPalAccount);
    }

    @Override
    public List<Account> getAccounts(User user) {
        return accountRepository.findByUserId(user.getId());
    }
}
