package com.tpg.smp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

public abstract class SmpController {
    @Autowired
    MessageSource messageSource;

    void addMessage(Model model, Locale locale, String msgKey, String[] objects) {
        String msg = messageSource.getMessage(msgKey, objects, locale);
        model.addAttribute(msgKey, msg);
    }
}
