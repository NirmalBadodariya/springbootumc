package com.example.springbootumc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import com.example.springbootumc.dao.UserDao;
import com.example.springbootumc.dao.UserDao;
import com.example.springbootumc.encryotion.AES;
import com.example.springbootumc.model.AddressBean;
import com.example.springbootumc.model.UserBean;
import com.example.springbootumc.model.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addNewUser(UserBean user) {

        List<UserRoles> roles = user.getRoles();
        for (AddressBean address : user.getAddresses()) {

            address.setUserBean(user);
        }
        for (UserRoles role : roles) {
            role.setUser(user);
        }
        user.setRoles(roles);

        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedPass = AES.encrypt(user.getPass(), secretKey);
        user.setPass(encryptedPass);
        userDao.save(user);

    }

    public int checkUser(String email, String pass) {
        UserBean user = userDao.getByEmail(email);
    int usertype = 0;
        System.out.println("id:  " + user.getId());

        int usertypeid = userDao.checkUserType(user.getId());

       List list = userDao.findByEmailAndPass(user.getEmail(),user.getPass());

        if (list != null && list.size() > 0 && usertypeid == 1) {
            usertype = 1;
        } else if (list != null && list.size() > 0 && usertypeid == 2) {
            usertype = 2;

        }
        return usertype;
    }

    public ArrayList getUserDetails() {
   return (ArrayList) userDao.findAll();

    }

    public void deleteUser(int userId) throws SecurityException{
        userDao.deleteAllById(Collections.singleton(userId));
    }

    public UserBean getLoggedinUserDetails(String email) {
        System.out.println("mail:"+ email);
        UserBean user =  userDao.getByEmail(email);
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String decryptedPass = AES.decrypt(user.getPass(), secretKey);
        user.setPass(decryptedPass);
        return user;
    }

    @Override
    public void changePass(int userId, String newPass) {
       UserBean user = (UserBean) userDao.getById(userId);
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedPass = AES.encrypt(newPass, secretKey);
        user.setPass(encryptedPass);
       userDao.save(user);
    }

    public void updateUser(UserBean user) {

        userDao.save(user);
    }

    public int checkForgotpassDetails(String dob, String securityAns) {
        List<UserBean> list = userDao.findByDobAndSecurityAns(dob, securityAns);
        if(list != null && list.size() > 0 && list.get(0).getId() !=0){
            return list.get(0).getId();
        }
        else {
            return 0;
        }
    }


    public ArrayList<UserBean> getRecentUsersList() {
        ArrayList<UserBean> userDetails = new ArrayList<>();
        List<Object[]> rows = userDao.getRecentUsersList();
        for (int i = 0; i < rows.size(); i++) {
            UserBean user = new UserBean();
            user.setId(Integer.parseInt(rows.get(i)[0].toString()));
            user.setEmail(rows.get(i)[2].toString());
            user.setFirstName(rows.get(i)[3].toString());
            userDetails.add(user);
        }
        return userDetails;
    }

    public List checkEmail(String email) {

        return userDao.findByEmail(email);

    }

}
