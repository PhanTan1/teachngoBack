package be.teachngo.service;


import be.teachngo.data.Account;
import be.teachngo.data.BankAccount;
import be.teachngo.data.PayPalAccount;
import be.teachngo.data.User;

import java.util.List;

public interface BankAccountService {


    BankAccount save(BankAccount bankAccount, User user);


    PayPalAccount save(PayPalAccount payPalAccount, User user);

    List<Account> getAccounts(User user);
}
