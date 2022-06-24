package com.example.springbootumc.service;

import com.example.springbootumc.dao.UserDao;
import com.example.springbootumc.model.AddressBean;
import com.example.springbootumc.model.UserBean;
import com.example.springbootumc.model.UserRoles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceTest {


    @InjectMocks
    private SignupServiceImpl userService;


    @Mock
    private UserDao userDao;

    @BeforeAll
    void init() {
        userService = new SignupServiceImpl();
    }

    @Test
    void addNewUserTest() throws Exception{
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
        user.setFirstName("swati");
        UserBean expected = new UserBean();
        expected.setFirstName("swati");
        when(userDao.save(user)).thenReturn(expected);

        UserBean actual = userService.addNewUser(user);
        assertNotNull(actual.toString());

        verify(userDao, atLeast(1)).save(any());
    }

    
    @Test
    void checkUserTest() throws Exception{
    	int usertype = 1; 
        UserBean expected = new UserBean();
        expected.setEmail("nrbadodariya@gmail.com");
        expected.setPass("Nirmal@123");
        when(userDao.getByEmail("nrbadodariya@gmail.com")).thenReturn(expected);
        UserBean actual = userDao.getByEmail("nrbadodariya@gmail.com");
        List list = userDao.findByEmailAndPass("nrbadodariya@gmail.com", "Nirmal@123");
        list.add(expected);
      int type =  userService.checkUser("nrbadodariya@gmail.com","Nirmal@123");
        verify(userDao, atLeast(1)).getByEmail(any());
    }
    @Test
    void anotherCheckUserTest() throws Exception{
    	int usertype = 1; 
        UserBean expected = new UserBean();
        expected.setEmail("nrbadodariya@gmail.com");
        expected.setPass("Nirmal@123");
        when(userDao.getByEmail("nrbadodariya@gmail.com")).thenReturn(null);
        UserBean actual = userDao.getByEmail("nrbadodariya@gmail.com");
        List list = userDao.findByEmailAndPass("nrbadodariya@gmail.com", "Nirmal@123");
        list.add(expected);
      int type =  userService.checkUser("nrbadodariya@gmail.com","Nirmal@123");
//        verify(userDao, atLeast(1)).getByEmail(any());
    }
    @Test
    void yetAnotherCheckUserTest() throws Exception{

    	List list = new ArrayList<>();
    	int usertypeid = 1; 
        UserBean expected = new UserBean();
        expected.setEmail("nrbadodariya@gmail.com");
        expected.setPass("Nirmal@123");
        when(userDao.checkUserType(0)).thenReturn(usertypeid);
        when(userDao.getByEmail("nrbadodariya@gmail.com")).thenReturn(expected);
        when(userDao.findByEmailAndPass("nrbadodariya@gmail.com", "Nirmal@123")).thenReturn(list);
        UserBean actual = userDao.getByEmail("nrbadodariya@gmail.com");
        list.add(expected);
        System.out.println("++++++++++"+ list.size()+"+++++++++++");
      int type =  userService.checkUser("nrbadodariya@gmail.com","Nirmal@123");
//        verify(userDao, atLeast(1)).getByEmail(any());
    }
    @Test
    void yetAnotherAnotherCheckUserTest() throws Exception{

    	List list = new ArrayList<>();
    	int usertypeid = 2; 
        UserBean expected = new UserBean();
        expected.setEmail("nrbadodariya@gmail.com");
        expected.setPass("Nirmal@123");
        when(userDao.checkUserType(0)).thenReturn(usertypeid);
        when(userDao.getByEmail("nrbadodariya@gmail.com")).thenReturn(expected);
        when(userDao.findByEmailAndPass("nrbadodariya@gmail.com", "Nirmal@123")).thenReturn(list);
        UserBean actual = userDao.getByEmail("nrbadodariya@gmail.com");
        list.add(expected);
        System.out.println("++++++++++"+ list.size()+"+++++++++++");
      int type =  userService.checkUser("nrbadodariya@gmail.com","Nirmal@123");
//        verify(userDao, atLeast(1)).getByEmail(any());
    }

    @Test
    void getUserDetailsTest() throws Exception {
        ArrayList<UserBean> bean = new ArrayList<>();
        UserBean user = new UserBean();
        user.setEmail("nrbadodariya@gmail.com");
        user.setPass("Nirmal@123");
        user.setFirstName("Nirmal");
        bean.add(user);
        when(userDao.findAll()).thenReturn(bean);

        ArrayList actualList = userService.getUserDetails();
        assertEquals(bean,actualList);
    }

    @Test
    void deleteUserTest() throws Exception {
        int id = 1;
        doNothing().when(userDao).deleteById(id);
        userService.deleteUser(id);
        verify(userDao,atLeast(1)).deleteById(id);

    }
    @Test
    void getLoggedinUserDetailsTest() throws Exception {
        UserBean expected = new UserBean();
        expected.setEmail("nrbadodariya@gmail.com");
        when(userDao.getByEmail("nrbadodariya@gmail.com")).thenReturn(expected);
        UserBean actual = userDao.getByEmail("nrbadodariya@gmail.com");
        Assertions.assertNotNull(actual.toString());
        userService.getLoggedinUserDetails("nrbadodariya@gmail.com");
        verify(userDao, atLeast(1)).getByEmail(any());
    }
    @Test
    void changePassTest() throws Exception {
        UserBean expected = new UserBean();
        expected.setId(1);
        expected.setPass("Nirmal@123");
        when(userDao.getById(1)).thenReturn(expected);
        userService.changePass(1,"Nirmal@123");
        assertNotNull(expected);
        verify(userDao, atLeast(1)).getById(any());
    }

    @Test
    void updateUserTest() throws Exception {
        UserBean user = new UserBean();
        user.setEmail("nrbadodariya@gmail.com");
        UserBean expected = new UserBean();
        expected.setEmail("nrbadodariya@gmail.com");
        when(userDao.save(user)).thenReturn(expected);

        userService.updateUser(user);
        verify(userDao, atLeast(1)).save(any());
    }

    @Test
    void checkForgotPassDetailsTest() throws Exception{
        List<UserBean> list = new ArrayList<>();
        UserBean user = new UserBean();
        user.setDob("2022-05-08");
        user.setSecurityAns("Nirmal");
        list.add(user);
        when(userDao.findByDobAndSecurityAns("2022-05-08","Nirmal")).thenReturn(list);
        list.get(0).setId(1);
        int type = userService.checkForgotpassDetails("2022-05-08","Nirmal");
        verify(userDao, atLeast(1)).findByDobAndSecurityAns("2022-05-08","Nirmal");

    }
    @Test
    void anotherCheckForgotPassDetailsTest() throws Exception{
        List<UserBean> list = new ArrayList<>();
        UserBean user = new UserBean();
        user.setDob("2022-05-08");
        user.setSecurityAns("Nirmal");
        list.add(user);
        when(userDao.findByDobAndSecurityAns("2022-05-08","Nirmal")).thenReturn(list);
        int type = userService.checkForgotpassDetails("2022-05-08","Nirmal");
        verify(userDao, atLeast(1)).findByDobAndSecurityAns("2022-05-08","Nirmal");

    }
    @Test
    void checkEmailTest() throws Exception{
        ArrayList<UserBean> bean = new ArrayList<>();
        UserBean user = new UserBean();
        user.setEmail("nrbadodariya@gmail.com");
        bean.add(user);
        when(userDao.findByEmail("nrbadodariya@gmail.com")).thenReturn(bean);
        userService.checkEmail("nrbadodariya@gmail.com");
        verify(userDao, atLeast(1)).findByEmail("nrbadodariya@gmail.com");

    }
    @Test
    void anotherCheckEmailTest() throws Exception{
        ArrayList<UserBean> bean = new ArrayList<>();
        UserBean user = new UserBean();
        user.setEmail("nrbadodariya@gmail.com");
       
        when(userDao.findByEmail("nrbadodariya@gmail.com")).thenReturn(bean);
        userService.checkEmail("nrbadodariya@gmail.com");
        verify(userDao, atLeast(1)).findByEmail("nrbadodariya@gmail.com");

    }
//    @Test
//    void getRecentUsersListTest() throws Exception {
//
//        List<Object[]> rows = new ArrayList<>();
//        ArrayList<UserBean> bean = new ArrayList<>();
//        UserBean user = new UserBean();
//        user.setId(1);
//        user.setFirstName("Nirmal");
//        user.setEmail("nrbadodariya@gmail.com");
//        bean.add(user);
//        UserBean list[] = new UserBean[1];
//        list[0] = user;
//        rows.add(list);
//        System.out.println("-------------------"+rows.get(0)[0]);
//        when(userDao.getRecentUsersList()).thenReturn(rows);
//
////        verify(userDao, atLeast(1)).getRecentUsersList();
//
//    }
}
