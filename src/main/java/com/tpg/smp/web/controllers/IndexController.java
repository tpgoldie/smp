package com.tpg.smp.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.tpg.smp.web.controllers.support.MessageKeyConstants.LOGIN_KEY;
import static com.tpg.smp.web.controllers.support.MessageKeyConstants.WELCOME_KEY;
import static com.tpg.smp.web.controllers.support.ViewConstants.INDEX_VIEW;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/smp")
public class IndexController extends SmpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

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

        addMessage(locale, model, WELCOME_KEY);
        addMessage(locale, model, LOGIN_KEY);

        response.setContentType(TEXT_HTML_VALUE);
        return INDEX_VIEW;
    }
}
