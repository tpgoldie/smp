package com.tpg.smp.web.controllers;

import com.google.common.base.Optional;
import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.web.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Locale;

import static com.tpg.smp.web.controllers.support.MessageKeyConstants.*;
import static com.tpg.smp.web.controllers.support.ModelAttributeKeyConstants.LOGIN_ERROR_ATTRIBUTE_KEY;
import static com.tpg.smp.web.controllers.support.ModelAttributeKeyConstants.WELCOME_ATTRIBUTE_KEY;
import static com.tpg.smp.web.controllers.support.SessionKeyConstants.USER_MODEL_SESSION_KEY;
import static com.tpg.smp.web.controllers.support.ViewConstants.INDEX_VIEW;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/smp")
@SessionAttributes({"userModel"})
public class LoginController extends SmpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login", consumes = APPLICATION_FORM_URLENCODED_VALUE, method = POST)
    public String login(@RequestHeader("Accept-Language") Locale locale, HttpSession httpSession,
                        @ModelAttribute("userModel") UserModel userModel,
                        Model model) {
        LOGGER.info("The client locale is {}.", locale);
        LOGGER.debug("The user is {}.", userModel.getUsername());

        Optional<AuthenticatedUser> authenticatedUser = authenticationService.authenticateUser(userModel);

        String msg;

        if (authenticatedUser.isPresent()) {
            String[] values = addUserDetails(authenticatedUser.get());
            msg = messageSource.getMessage(USER_WELCOME_KEY, values, locale);
        }
        else {
            String[] values = new String[0];
            msg = messageSource.getMessage(WELCOME_KEY, values, locale);
            UserModel defaultUserModel = new UserModel();

            model.addAttribute(USER_MODEL_SESSION_KEY, defaultUserModel);
            httpSession.setAttribute(USER_MODEL_SESSION_KEY, defaultUserModel);
        }

        model.addAttribute(WELCOME_ATTRIBUTE_KEY, msg);

        if (!authenticatedUser.isPresent()) {
            addErrorMessage(model, locale, LOGIN_ERROR_KEY);
        }

        return INDEX_VIEW;
    }

    private String[] addUserDetails(AuthenticatedUser authenticatedUser) {
        String[] values = new String[1];
        values[0] = authenticatedUser.getFirstName();
        return values;
    }

    private void addErrorMessage(Model model, Locale locale, String errorKey) {
        String msg = messageSource.getMessage(errorKey, new String[0], locale);
        model.addAttribute(LOGIN_ERROR_ATTRIBUTE_KEY, msg);
    }
}
