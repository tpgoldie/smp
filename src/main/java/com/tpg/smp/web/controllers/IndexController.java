package com.tpg.smp.web.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

import static com.tpg.smp.web.controllers.support.MessageKeyConstants.WELCOME_KEY;
import static com.tpg.smp.web.controllers.support.ViewConstants.INDEX_VIEW;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/smp")
public class IndexController {
    private static final Logger LOGGER = LogManager.getLogger(IndexController.class);

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/index", consumes = MediaType.TEXT_HTML_VALUE, method = GET)
    public String getIndex(@RequestHeader("Accept-Language") Locale locale, Model model) {
        LOGGER.info("The client locale is {}.", locale);

        String msg = messageSource.getMessage(WELCOME_KEY, new Object[0], locale);
        model.addAttribute(WELCOME_KEY, msg);

        return INDEX_VIEW;
    }
}
