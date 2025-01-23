package com.example.demo.Controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.Exception.AccountNotFoundException;
import com.example.demo.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    CustomerService customerService;

    @GetMapping("/insertBankNumber")
    public void insertBankNumber(@RequestParam String bankNumber, Model model) throws AccountNotFoundException {
        customerService.createAccount(bankNumber);
    }

}
