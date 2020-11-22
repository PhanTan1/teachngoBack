package be.teachngo.controller;


import be.teachngo.data.Account;
import be.teachngo.data.BankAccount;
import be.teachngo.data.PayPalAccount;
import be.teachngo.data.User;
import be.teachngo.repository.StudentRepository;
import be.teachngo.repository.TeacherRepository;
import be.teachngo.repository.UserRepository;
import be.teachngo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class BankAccountController {


    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/bankaccount")
    public List<Account> getAccounts() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User user;
        if (authentication.getPrincipal() instanceof User) {
            user = userRepository.getOne(((User) authentication.getPrincipal()).getId());
        } else {
            throw new IllegalArgumentException("Can't find authenticated user");
        }
        return bankAccountService.getAccounts(user);
    }
    @PostMapping("/bankaccount")
    public BankAccount addBanckAccount(@RequestBody BankAccount bankAccount) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User user;
        if (authentication.getPrincipal() instanceof User) {
            user = userRepository.getOne(((User) authentication.getPrincipal()).getId());
        } else {
            throw new IllegalArgumentException("Can't find authenticated user");
        }
        return bankAccountService.save(bankAccount, user);
    }

    @PostMapping("/paypalaccount")
    public PayPalAccount addPaypalAccount(@RequestBody PayPalAccount payPalAccount) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        final User user;
        if (authentication.getPrincipal() instanceof User) {
            user = userRepository.getOne(((User) authentication.getPrincipal()).getId());
        } else {
            throw new IllegalArgumentException("Can't find authenticated user");
        }
        return bankAccountService.save(payPalAccount, user);
    }
}
