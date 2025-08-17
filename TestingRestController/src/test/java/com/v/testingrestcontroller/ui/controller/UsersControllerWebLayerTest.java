package com.v.testingrestcontroller.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.v.testingrestcontroller.service.UsersService;
import com.v.testingrestcontroller.shared.UserDto;
import com.v.testingrestcontroller.ui.controllers.UsersController;
import com.v.testingrestcontroller.ui.request.UserDetailsRequestModel;
import com.v.testingrestcontroller.ui.response.UserRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

@WebMvcTest(controllers = UsersController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
//@AutoConfigureMockMvc(addFilters = false)
public class UsersControllerWebLayerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UsersService usersService;

    @Test
    @DisplayName("User can be created")
    void testCreateUser_whenValidDetailsProvided_thenCreateUser() throws Exception {
        //Arrange
        UserDetailsRequestModel requestModel=new UserDetailsRequestModel();
        requestModel.setFirstName("Bruce");
        requestModel.setLastName("Wayne");
        requestModel.setEmail("im@batman");
        requestModel.setPassword("darkknight");
        requestModel.setRepeatPassword("darkknight");

        //one way
//        UserDto userDto=new UserDto();
//        userDto.setFirstName("Bruce");
//        userDto.setLastName("Wayne");
//        userDto.setEmail("im@batman");
//        userDto.setPassword("darkknight");
//        userDto.setUserId(UUID.randomUUID().toString());

        //using model mapper to map it to same fields of another object
        UserDto userDto = new ModelMapper().map(requestModel, UserDto.class);
        userDto.setUserId(UUID.randomUUID().toString());

        Mockito.when(usersService.createUser(Mockito.any(UserDto.class))).thenReturn(userDto);


        //preparing mock http request
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                //Configure Content of http body
                .content(new ObjectMapper().writeValueAsString(requestModel));//this will convert java object into string

        //Act

        //Using mockmvc to execute request we have prepared

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(); // resturn response as string

        //Converting this to return type of controller
        UserRest createdUser = new ObjectMapper().readValue(contentAsString, UserRest.class);





        //Assert

        Assertions.assertEquals(requestModel.getFirstName(),createdUser.getFirstName(),"The returned user firstname is incorrect");

        Assertions.assertFalse(createdUser.getUserId().isBlank(),"User id is must");

    }
}
