package com.tpg.smp.web.controllers.actions;

import com.tpg.smp.web.controllers.forms.StudentRegistrationForm;
import com.tpg.smp.web.model.UserModel;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.nio.charset.Charset;

import static java.util.Locale.UK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class PerformStudentRegistration {
    private static final MediaType contentType = new MediaType(APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private final ResultActions resultActions;

    public PerformStudentRegistration(MockMvc mockMvc, MappingJackson2HttpMessageConverter jsonHandler, UserModel userModel, StudentRegistrationForm registrationForm) throws Exception {
        String jsonOutput = json(jsonHandler, registrationForm);

        resultActions = mockMvc.perform(post("/smp/student/register")
                .content(jsonOutput)
                .contentType(contentType)
                .accept(contentType)
                .locale(UK)
                .header("Accept-Language", "en_GB")
                .sessionAttr("userModel", userModel))
                .andDo(print());
    }

    public ResultActions resultActions() { return resultActions; }

    private String json(MappingJackson2HttpMessageConverter jsonHandler, StudentRegistrationForm form) throws IOException {
        return jsonHandler.getObjectMapper().writeValueAsString(form);
    }
}
