package be.teachngo.service;


import be.teachngo.data.BankAccount;
import be.teachngo.data.PayPalAccount;
import be.teachngo.data.User;

public interface BankAccountService {


    BankAccount save(BankAccount bankAccount, User user);


    PayPalAccount save(PayPalAccount payPalAccount, User user);
}
