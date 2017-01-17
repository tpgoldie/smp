package com.tpg.smp.web.controllers;

import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.web.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

import static com.tpg.smp.web.controllers.support.MessageKeyConstants.USER_WELCOME_KEY;
import static com.tpg.smp.web.controllers.support.ViewConstants.INDEX_VIEW;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/smp")
public class LoginController extends SmpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login", consumes = APPLICATION_FORM_URLENCODED_VALUE, method = POST)
    public String login(@RequestHeader("Accept-Language") Locale locale, @ModelAttribute("userModel") UserModel userModel, Model model) {
        LOGGER.info("The client locale is {}.", locale);

        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authenticationService.authenticateUser(userModel).get();

        addMessage(locale, authenticatedUser, model, USER_WELCOME_KEY);

        return INDEX_VIEW;
    }

    private void addMessage(Locale locale, AuthenticatedUser authenticatedUser, Model model, String userWelcomeKey) {
        String[] values = new String[1];
        values[0] = authenticatedUser.getFirstName();

        String msg = messageSource.getMessage(userWelcomeKey, values, locale);
        model.addAttribute(userWelcomeKey, msg);
    }
}
