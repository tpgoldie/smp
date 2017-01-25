package com.tpg.smp.web.controllers;

import com.google.common.base.Optional;
import com.tpg.smp.auth.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.google.common.base.Optional.of;
import static com.tpg.smp.web.controllers.support.MessageKeyConstants.LOGIN_KEY;
import static com.tpg.smp.web.controllers.support.MessageKeyConstants.WELCOME_KEY;
import static com.tpg.smp.web.controllers.support.ModelAttributeKeyConstants.LOGIN_ATTRIBUTE_KEY;
import static com.tpg.smp.web.controllers.support.ModelAttributeKeyConstants.LOGIN_ERROR_ATTRIBUTE_KEY;
import static com.tpg.smp.web.controllers.support.ModelAttributeKeyConstants.WELCOME_ATTRIBUTE_KEY;
import static com.tpg.smp.web.controllers.support.ViewConstants.INDEX_VIEW;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/smp")
public class IndexController extends SmpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    public IndexController(MessageSource messageSource, AuthenticationService authenticationService) {
        super(messageSource, authenticationService);
    }

    @RequestMapping(value = "/", consumes = TEXT_HTML_VALUE, method = GET)
    public String home(@RequestHeader("Accept-Language") Locale locale, Model model, HttpServletResponse response) {
        return handleHomeRequest(locale, model, response);
    }

    @RequestMapping(value = "/index", consumes = TEXT_HTML_VALUE, method = GET)
    public String index(@RequestHeader("Accept-Language") Locale locale, Model model, HttpServletResponse response) {
        return handleHomeRequest(locale, model, response);
    }

    private String handleHomeRequest(Locale locale, Model model, HttpServletResponse response) {
        LOGGER.info("The client locale is {}.", locale);

        String[] noMsgParameters = new String[0];

        addMessage(model, locale, WELCOME_KEY, WELCOME_ATTRIBUTE_KEY, noMsgParameters);
        addMessage(model, locale, LOGIN_KEY, LOGIN_ATTRIBUTE_KEY, noMsgParameters);

        response.setContentType(TEXT_HTML_VALUE);
        return INDEX_VIEW;
    }
}
