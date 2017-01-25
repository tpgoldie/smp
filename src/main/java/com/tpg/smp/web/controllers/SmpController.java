package com.tpg.smp.web.controllers;

import com.google.common.base.Optional;
import com.tpg.smp.auth.AuthenticationService;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

public abstract class SmpController {
    MessageSource messageSource;

    AuthenticationService authenticationService;

    SmpController(MessageSource messageSource, AuthenticationService authenticationService) {
        this.messageSource = messageSource;
        this.authenticationService = authenticationService;
    }

    void addMessage(Model model, Locale locale, String msgKey, String attributeKey, String[]objects) {
        String msg = messageSource.getMessage(msgKey, objects, locale);
        model.addAttribute(attributeKey, msg);
    }
}
