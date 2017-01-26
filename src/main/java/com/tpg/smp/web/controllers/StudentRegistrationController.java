package com.tpg.smp.web.controllers;

import com.google.common.base.Optional;
import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.domain.Course;
import com.tpg.smp.domain.Student;
import com.tpg.smp.services.CoursesQueryService;
import com.tpg.smp.services.ServiceOutcome;
import com.tpg.smp.services.Success;
import com.tpg.smp.services.registration.StudentRegistrationModel;
import com.tpg.smp.services.registration.StudentRegistrationService;
import com.tpg.smp.web.controllers.forms.StudentRegistrationForm;
import com.tpg.smp.web.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

import static com.tpg.smp.web.controllers.support.MessageKeyConstants.STUDENT_REGISTRATION_FAILURE_KEY;
import static com.tpg.smp.web.controllers.support.MessageKeyConstants.STUDENT_REGISTRATION_SUCCESS_KEY;
import static com.tpg.smp.web.controllers.support.ModelAttributeKeyConstants.STUDENT_REGISTRATION_FAILURE_ATTRIBUTE_KEY;
import static com.tpg.smp.web.controllers.support.ModelAttributeKeyConstants.STUDENT_REGISTRATION_MODEL_ATTRIBUTE_KEY;
import static com.tpg.smp.web.controllers.support.ModelAttributeKeyConstants.STUDENT_REGISTRATION_SUCCESS_ATTRIBUTE_KEY;
import static com.tpg.smp.web.controllers.support.ViewConstants.STUDENT_REGISTRATION_FAILURE_VIEW;
import static com.tpg.smp.web.controllers.support.ViewConstants.STUDENT_REGISTRATION_SUCCESS_VIEW;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/smp/student")
@SessionAttributes({"userModel"})
public class StudentRegistrationController extends SmpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRegistrationController.class);

    private Validator validator;

    private StudentRegistrationService studentRegistrationService;

    private CoursesQueryService coursesQueryService;

    @Autowired
    public StudentRegistrationController(MessageSource messageSource, Validator validator,
                                         AuthenticationService authenticationService,
                                         StudentRegistrationService studentRegistrationService,
                                         CoursesQueryService coursesQueryService) {
        super(messageSource, authenticationService);

        this.validator = validator;
        this.studentRegistrationService = studentRegistrationService;
        this.coursesQueryService = coursesQueryService;
    }

    @RequestMapping(value = "/register", consumes = APPLICATION_JSON_UTF8_VALUE, method = POST)
    public String register(@RequestHeader("Accept-Language") Locale locale,
                            @ModelAttribute("userModel") UserModel userModel,
                            @RequestBody StudentRegistrationForm registrationForm,
                            Model model,
                            BindingResult bindingResult) throws IOException {
        LOGGER.debug("Registering student ...");
        LOGGER.debug("The user is {}.", userModel.getUsername());

        Optional<AuthenticatedUser> checkedUser = authenticationService.authenticateUser(userModel);

        String view = STUDENT_REGISTRATION_SUCCESS_VIEW;

        if (checkedUser.isPresent()) {
            boolean successful = handleStudentRegistration(model, locale, bindingResult, registrationForm);

            if (!successful) {
                return STUDENT_REGISTRATION_FAILURE_VIEW;
            }
        }

        return view;
    }

    private boolean handleStudentRegistration(Model model, Locale locale, BindingResult bindingResult, StudentRegistrationForm registrationForm) {
        LOGGER.debug(String.format("Form instance is %s", registrationForm.getName()));

        Set<ConstraintViolation<StudentRegistrationForm>> violations = validator.validate(registrationForm);

        if (!violations.isEmpty()) {
            violations.stream().forEach(violation -> new StudentRegistrationFormError(bindingResult, violation));

            addMessage(model, locale, STUDENT_REGISTRATION_FAILURE_KEY, STUDENT_REGISTRATION_FAILURE_ATTRIBUTE_KEY, new String[0]);

            return false;
        }

        StudentRegistrationModel registrationModel = new StudentRegistrationModel(registrationForm);

        Optional<Course> course = coursesQueryService.findCourseByReferenceNumber(registrationForm.getCourseReferenceNumber());

        if (course.isPresent()) {
            registrationModel.setCourse(course.get());
        }
        else {
            bindingResult.addError(new CourseReferenceNumberError(messageSource, "course.missing", locale));

            addMessage(model, locale, STUDENT_REGISTRATION_FAILURE_KEY, STUDENT_REGISTRATION_FAILURE_ATTRIBUTE_KEY, new String[0]);

            return false;
        }

        ServiceOutcome outcome = studentRegistrationService.registerStudent(registrationModel);
        boolean successful = outcome instanceof Success;

        String[] keys = new String[2];

        if (successful) {
            keys[0] = STUDENT_REGISTRATION_SUCCESS_KEY;
            keys[1] = STUDENT_REGISTRATION_SUCCESS_ATTRIBUTE_KEY;
        }
        else {
            keys[0] = STUDENT_REGISTRATION_FAILURE_KEY;
            keys[1] = STUDENT_REGISTRATION_FAILURE_ATTRIBUTE_KEY;
        }

        addMessage(model, locale, keys[0], keys[1], new String[0]);

        model.addAttribute(STUDENT_REGISTRATION_MODEL_ATTRIBUTE_KEY, registrationModel);

        return successful;
    }

    private static class StudentRegistrationFormError extends FieldError {
        StudentRegistrationFormError(BindingResult bindingResult, ConstraintViolation<StudentRegistrationForm> violation) {
            super("studentRegistrationForm", violation.getPropertyPath().toString(), violation.getMessage());

            bindingResult.addError(this);
        }
    }

    private static class CourseReferenceNumberError extends FieldError {
        CourseReferenceNumberError(MessageSource messageSource, String messageKey, Locale locale) {
            super("studentRegistrationForm", "courseReferenceNumber", messageSource.getMessage(messageKey, new String[0], locale));
        }
    }
}
