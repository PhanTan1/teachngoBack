package be.teachngo.controller;


import be.teachngo.data.Payement;
import be.teachngo.service.PayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class PaymentController {

    @Autowired
    private PayementService payementService;

    @PostMapping("/payements")
    Payement newPayement(@RequestBody Payement payement) {
        return payementService.makeAPayement(payement);
    }

    @GetMapping("/payements")
    List<Payement> getAllPayements() {
        return payementService.getAllPayements();
    }

}
