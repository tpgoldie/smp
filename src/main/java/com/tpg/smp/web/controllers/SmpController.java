package com.tpg.smp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

public abstract class SmpController {
    @Autowired
    protected MessageSource messageSource;

    protected void addMessage(Locale locale, Model model, String msgKey) {
        String msg = messageSource.getMessage(msgKey, new Object[0], locale);
        model.addAttribute(msgKey, msg);
    }
}
