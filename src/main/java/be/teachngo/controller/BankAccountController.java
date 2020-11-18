package be.teachngo.controller;


import be.teachngo.data.BankAccount;
import be.teachngo.data.PayPalAccount;
import be.teachngo.data.User;
import be.teachngo.repository.StudentRepository;
import be.teachngo.repository.TeacherRepository;
import be.teachngo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/bankaccount")
    public BankAccount addBanckAccount(@RequestBody BankAccount bankAccount) {
        // TODO : remove this line after the implementation of spring security
        User user = teacherRepository.findAll().get(0);
        return bankAccountService.save(bankAccount, user);
    }

    @PostMapping("/paypalaccount")
    public PayPalAccount addPaypalAccount(@RequestBody PayPalAccount payPalAccount) {
        // TODO : remove this line after the implementation of spring security
        User user = studentRepository.findAll().get(0);
        return bankAccountService.save(payPalAccount, user);
    }
}
