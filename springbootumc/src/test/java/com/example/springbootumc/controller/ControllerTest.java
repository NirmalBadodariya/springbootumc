package com.example.springbootumc.controller;

import com.example.springbootumc.model.AddressBean;
import com.example.springbootumc.model.UserBean;
import com.example.springbootumc.model.UserRoles;
import com.example.springbootumc.service.SignupServiceImpl;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private BindingResult mockBindingResult;
    @MockBean
    private SignupServiceImpl userService;

    protected MockHttpSession session;
    @Test
    public void checkUserTest() throws Exception{
        UserBean user = new UserBean();
        user.setId(1);
        user.setFirstName("swati");
        user.setEmail("nrbadodariya@gmail.com");
        user.setPass("Nirmal@123");
        when(userService.checkUser("nrbadodariya@gmail.com","Nirmal@123")).thenReturn(1);
        	

        this.mockMvc
                .perform(post("/Login").param("email","nrbadodariya@gmail.com").param("pass","Nirmal@123"))
                .andExpect(redirectedUrl("userHome"));

        verify(userService, atLeast(1)).checkUser("nrbadodariya@gmail.com","Nirmal@123");
    }
    @Test
    public void anotherCheckUserTest() throws Exception{
        UserBean user = new UserBean();
        user.setId(1);
        user.setFirstName("swati");
        user.setEmail("nrbadodariya@gmail.com");
        user.setPass("Nirmal@123");
        when(userService.checkUser("nrbadodariya@gmail.com","Nirmal@123")).thenReturn(2);


        this.mockMvc
                .perform(post("/Login").param("email","nrbadodariya@gmail.com").param("pass","Nirmal@123"))
                .andExpect(redirectedUrl("adminHome"));

        verify(userService, atLeast(1)).checkUser("nrbadodariya@gmail.com","Nirmal@123");
    }
    @Test
    public void yetAnotherCheckUserTest() throws Exception{
        UserBean user = new UserBean();
        user.setId(1);
        user.setFirstName("swati");
        user.setEmail("nrbadodariya@gmail.com");
        user.setPass("Nirmal@123");
        when(userService.checkUser("nrbadodariya@gmail.com","Nirmal@123")).thenReturn(0);


        this.mockMvc
                .perform(post("/Login").param("email","nrbadodariya@gmail.com").param("pass","Nirmal@123"))
                .andExpect(status().isOk());

        verify(userService, atLeast(1)).checkUser("nrbadodariya@gmail.com","Nirmal@123");
    }

    @Test
    public void getUserDetailsTest() throws Exception{
        ArrayList<UserBean> userDetails = new ArrayList<>();
        UserBean user = new UserBean();
        user.setFirstName("Nirmal");
        user.setEmail("nrbadodariya@gmail.com");
        user.setId(1);
        userDetails.add(user);
        when(userService.getUserDetails()).thenReturn(userDetails);
        this.mockMvc
                .perform(post("/UsersDetails"))
                .andExpect(status().isOk());

        verify(userService, atLeast(1)).getUserDetails();
    }

    @Test
    public void deleteUserTest() throws Exception{
        int userId =1 ;
        this.mockMvc
                .perform(post("/DeleteUser").param("userId","1"))
                .andExpect(status().isOk());
        doNothing().when(userService).deleteUser(userId);

        verify(userService, atLeast(1)).deleteUser(userId);
    }

    @Test
    public void forgotPassTest() throws Exception{
        this.mockMvc
                .perform(post("/ForgotPass").param("dob","2022-05-08").param("securityAns","Nirmal"))
                .andExpect(status().isOk());
        when(userService.checkForgotpassDetails("2022-05-08","Nirmal")).thenReturn(111);
        verify(userService, atLeast(1)).checkForgotpassDetails("2022-05-08","Nirmal");
    }
    @Test
    public void anotherforgotPassTest() throws Exception{
        this.mockMvc
                .perform(post("/ForgotPass").param("dob","2022-05-08").param("securityAns","Nirmal"))
                .andExpect(status().isOk());
        when(userService.checkForgotpassDetails("2022-05-08","Nirmal")).thenReturn(0);
        verify(userService, atLeast(1)).checkForgotpassDetails("2022-05-08","Nirmal");
    }
    @Test
    public void getRecentUsersTest() throws Exception{

        ArrayList<UserBean> userDetails = new ArrayList<>();
        UserBean user = new UserBean();
        user.setFirstName("Nirmal");
        user.setEmail("nrbadodariya@gmail.com");
        user.setId(1);
        userDetails.add(user);
        when(userService.getRecentUsersList()).thenReturn(userDetails);
        this.mockMvc
                .perform(post("/getRecentUsers"))
                .andExpect(status().isOk());

        verify(userService, atLeast(1)).getRecentUsersList();

    }
    @Test
    public void loginPageTest() throws Exception {

        this.mockMvc
                .perform(post("/index"))
                .andExpect(status().isOk());
    }
    @Test
    public void anotherLoginPageTest() throws Exception {

        this.mockMvc
                .perform(post("/"))
                .andExpect(status().isOk());
    }
    @Test
    public void gotoRegisterTest() throws Exception {

        this.mockMvc
                .perform(post("/register"))
                .andExpect(status().isOk());
    }
    @Test
    public void gotoForgotPassTest() throws Exception {

        this.mockMvc
                .perform(post("/forgotpass"))
                .andExpect(status().isOk());
    }
    @Test
    public void logOutTest() throws Exception {

        this.mockMvc
                .perform(post("/Logout"))
                .andExpect(redirectedUrl("index"));
    }
    @Test
    public void torecentlyregisteredTest() throws Exception {

        this.mockMvc
                .perform(post("/torecentlyregistered"))
                .andExpect(status().isOk());
    }
    @Test
    public void userHomeTest() throws Exception {
        session = new MockHttpSession();

        this.mockMvc
                .perform(post("/userHome").sessionAttr("role",0))
                .andExpect(status().isOk());
        this.mockMvc
                .perform(post("/backToHome").sessionAttr("role",0))
                .andExpect(status().isOk());
    }
    @Test
    public void anotherUserHomeTest() throws Exception {
        session = new MockHttpSession();

        this.mockMvc
                .perform(post("/userHome").sessionAttr("role",1))
                .andExpect(status().isOk());
        this.mockMvc
                .perform(post("/backToHome").sessionAttr("role",1))
                .andExpect(status().isOk());
    }
    @Test
    public void adminHomeTest() throws Exception {
        this.mockMvc
                .perform(post("/adminHome").sessionAttr("role",2))
                .andExpect(status().isOk());
    }
    
    @Test
    public void anotherAdminHomeTest() throws Exception {
        this.mockMvc
                .perform(post("/adminHome").sessionAttr("role",3))
                .andExpect(status().isOk());
    }

    @Test
    public void editDetailsTest() throws Exception {
        session = new MockHttpSession();
        UserBean user = new UserBean();
        user.setFirstName("Nirmal");
        user.setEmail("nrbadodariya@gmail.com");
        user.setId(1);

        when(userService.getLoggedinUserDetails("nrbadodariya@gmail.com")).thenReturn(user);
        this.mockMvc
                .perform(post("/EditDetails").sessionAttr("email", "nrbadodariya@gmail.com"))
                .andExpect(status().isOk());
        verify(userService, atLeast(1)).getLoggedinUserDetails("nrbadodariya@gmail.com");
    }
    @Test
    public void changePassTest() throws Exception{
        this.mockMvc
                .perform(post("/ChangePass").param("newPass","Nirmal@123").sessionAttr("userIdForPassChange",1))
                .andExpect(status().isOk());
        doNothing().when(userService).changePass(1,"Nirmal@123");

        verify(userService, atLeast(1)).changePass(1,"Nirmal@123");
    }
    @Test
    public void emailFromUserTest() throws Exception{
        this.mockMvc
                .perform(get("/emailFromUser").param("email","nrbadodariya@gmail.com"))
                .andExpect(redirectedUrl("/EditDetails"));
    }

//    @Test
//    public void checkEmailAvailabilityTest() throws Exception{
//
//        this.mockMvc
//                .perform(post("/emailFromUser").param("email","nrbadodariya@gmail.com").sessionAttr("email", "nrbadodariya@gmail.com"))
//                .andExpect(redirectedUrl("/EditDetails"));
//        when(userService.checkEmail("nrbadodariya@gmail.com")).thenReturn("true");
//        verify(userService, atLeast(1)).checkEmail("nrbadodariya@gmail.com");
//
//    }
    @Test
    public void signupTest() throws Exception{

        AddressBean addressBean = new AddressBean();
        addressBean.setAddressLine1("Mumbai");
        addressBean.setAddressLine2("india");
        addressBean.setPincode(111111);
        addressBean.setCity("Ahmedabad");
        addressBean.setState("gujarat");
        List<AddressBean> addresses = new ArrayList<>();

        UserRoles roles = new UserRoles();
        roles.setRole(1);
        List<UserRoles> rolesinUser = new ArrayList<>();
        rolesinUser.add(roles);
        addresses.add(addressBean);
        UserBean user = new UserBean();
        user.setAddresses(addresses);
        user.setRoles(rolesinUser);
        user.setId(1);
        user.setDob("2022-05-08");
        user.setSecurityAns("Nirmal");
        user.setEmail("nrbadodariya@gmail.com");
        user.setGender("M");
        user.setPass("Nirmal@123");
        user.setPhone("1234567890");
        user.setFirstName("Nirmal");
        user.setLastName("Badodariya");
        InputStream file = new FileInputStream("C:\\Users\\nrbad\\OneDrive\\Pictures\\Screenshots\\image.png");
        MockMultipartFile firstFile = new MockMultipartFile("profileimage", file);
        mockMvc.perform(fileUpload("/Signup")
                .file(firstFile).contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .param("aid" ,"1")
                .flashAttr("user",user)
                .sessionAttr("role",2)).andExpect(redirectedUrl("adminHome"));

        Mockito.lenient().when(mockBindingResult.hasErrors()).thenReturn(true);
        doNothing().when(userService).updateUser(user);
    }

    @Test
    public void anotherSignupTest() throws Exception{

        AddressBean addressBean = new AddressBean();
        addressBean.setAddressLine1("Mumbai");
        addressBean.setAddressLine2("india");
        addressBean.setPincode(111111);
        List<AddressBean> addresses = new ArrayList<>();
        	
        UserRoles roles = new UserRoles();
        roles.setRole(1);
        List<UserRoles> rolesinUser = new ArrayList<>();
        rolesinUser.add(roles);
        addresses.add(addressBean);
        UserBean user = new UserBean();
        user.setAddresses(addresses);
        user.setRoles(rolesinUser);
        user.setId(0);
        user.setDob("2022-05-08");
        user.setSecurityAns("Nirmal");
        user.setEmail("nrbadodariya@gmail.com");
        user.setGender("M");
        user.setPass("Nirmal@123");
        user.setPhone("1234567890");
        user.setFirstName("Nirmal");
        user.setLastName("Badodariya");
        InputStream file = new FileInputStream("C:\\Users\\nrbad\\OneDrive\\Pictures\\Screenshots\\image.png");
        MockMultipartFile firstFile = new MockMultipartFile("profileimage", file);
        mockMvc.perform(fileUpload("/Signup")
                .file(firstFile).contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .param("aid" ,"1")
                .flashAttr("user",user)
                .sessionAttr("role",0)).andExpect(redirectedUrl("userHome"));

        Mockito.lenient().when(mockBindingResult.hasErrors()).thenReturn(true);
            userService.addNewUser(user);
//            doNothing().when(userService).addNewUser(user);

//

//        verify(userService, atLeast(1)).addNewUser(user);
    }
    @Test
    public void yetAnotherSignupTest() throws Exception{
    		
        AddressBean addressBean = new AddressBean();
        addressBean.setAddressLine1("Mumbai");
        addressBean.setAddressLine2("india");
        addressBean.setPincode(111111);addressBean.setCity("Ahmedabad");
        addressBean.setState("gujarat");

        List<AddressBean> addresses = new ArrayList<>();

        UserRoles roles = new UserRoles();
        roles.setRole(1);
        List<UserRoles> rolesinUser = new ArrayList<>();
        rolesinUser.add(roles);
        addresses.add(addressBean);
        UserBean user = new UserBean();
        user.setAddresses(addresses);
        user.setRoles(rolesinUser);
        user.setId(0);
        user.setDob("2022-05-08");
        user.setSecurityAns("Nirmal");
        user.setEmail("nrbadodariya@gmail.com");
        user.setGender("M");
        user.setPass("Nirmal@123");
        user.setPhone("1234567890");
        user.setFirstName("Ni");
        user.setLastName("Badodariya");
        InputStream file = new FileInputStream("C:\\Users\\nrbad\\OneDrive\\Pictures\\Screenshots\\image.png");
        MockMultipartFile firstFile = new MockMultipartFile("profileimage", file);
        mockMvc.perform(fileUpload("/Signup")
                .file(firstFile).contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .param("aid" ,"1")
                .flashAttr("user",user)
                .sessionAttr("role",0)).andExpect(status().isOk());

        Mockito.lenient().when(mockBindingResult.hasErrors()).thenReturn(true);

//        doNothing().when(userService).addNewUser(user);

    }
    
    @Test
    public void oneAnotherSignupTest() throws Exception{

        AddressBean addressBean = new AddressBean();
        addressBean.setAddressLine1("Mumbai");
        addressBean.setAddressLine2("india");
        addressBean.setPincode(111111);
        List<AddressBean> addresses = new ArrayList<>();
        	
        UserRoles roles = new UserRoles();
        roles.setRole(1);
        List<UserRoles> rolesinUser = new ArrayList<>();
        rolesinUser.add(roles);
        addresses.add(addressBean);
        UserBean user = new UserBean();
        user.setAddresses(addresses);
        user.setRoles(rolesinUser);
        user.setId(0);
        user.setDob("2022-05-08");
        user.setSecurityAns("Nirmal");
        user.setEmail("nrbadodariya@gmail.com");
        user.setGender("M");
        user.setPass("Nirmal@123");
        user.setPhone("1234567890");
        user.setFirstName("Nirmal");
        user.setLastName("Badodariya");
        InputStream file = new FileInputStream("C:\\Users\\nrbad\\OneDrive\\Pictures\\Screenshots\\image.png");
        MockMultipartFile firstFile = new MockMultipartFile("profileimage", file);
        mockMvc.perform(fileUpload("/Signup")
                .file(firstFile).contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .param("aid" ,"1")
                .flashAttr("user",user)
                .sessionAttr("role",10)).andExpect(redirectedUrl("register"));

//        Mockito.lenient().when(mockBindingResult.hasErrors()).thenReturn(true);
//            userService.addNewUser(user);
//            doNothing().when(userService).addNewUser(user);

//

//        verify(userService, atLeast(1)).addNewUser(user);
    }
}
