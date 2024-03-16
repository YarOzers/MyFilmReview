package com.filmreview.springbootproject.controller;

import com.filmreview.springbootproject.model.RegistrationRequest;
import com.filmreview.springbootproject.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {


    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showRegistration(Model model) {
        RegistrationRequest request = new RegistrationRequest();
        model.addAttribute("request", request);
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute("request") @Valid RegistrationRequest request,
                               BindingResult result) {
        if(result.hasErrors()) {
            return "/registration";
        }
        registrationService.register(request);
        return "login";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "login";
    }
}
