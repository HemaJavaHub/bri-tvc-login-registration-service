package com.authenticate.loginservice;

import TestUtils.TestUsers;
import com.authenticate.loginservice.controllers.UserController;
import com.authenticate.loginservice.models.User;
import com.authenticate.loginservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void postingUser_savesTheUser() throws Exception{
        User user = TestUsers.getUsers().get(0);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));
        mockMvc.perform(builder).andExpect(status().isOk());
        verify(userService, times(1)).addUser(any(User.class));
    }

    @Test
    public void loginValidation_ReturnsSuccessOrFailure() throws Exception{
        List<User> users = new ArrayList<>();
        users.add(TestUsers.getUsers().get(0));
        users.add(TestUsers.getUsers().get(1));

        when(userService.validateUserLogin("user1@email.com", "user1"))
                .thenReturn(true);

    }


}
