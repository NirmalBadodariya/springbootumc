package com.example.springbootumc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.validation.Valid;
//import com.example.springbootumc.configurations.UserPrincipal;
import com.example.springbootumc.configurations.UserPrincipal;
import com.example.springbootumc.model.AddressBean;
import com.example.springbootumc.model.UserBean;
import com.example.springbootumc.model.UserRoles;
import com.example.springbootumc.service.SignupService;
import com.example.springbootumc.service.myUserDetailsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
    @Autowired
    private myUserDetailsService myUserDetailsService;
    @Autowired
    private SignupService signupServiceImpl;
    @RequestMapping("/setSession")
    public String setSession(){
        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       UserBean user =  userDetails.returnUser();

      // session.setAttribute("email",user.getEmail());
        if(user.getUserRole()==1){
            System.out.println("goto user");
            return "userHome";
        }
        else if (user.getUserRole()==2){
            System.out.println("goto admin");
            return "adminHome";
        }
        return null;
    }
    @RequestMapping(path = { "/" ,"/index" })
    public String loginPage() {
        return "index";

    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(Model model) {
//
//        return "userHome";
//    }
    @RequestMapping("/register")
    public String gotoRegister() {

        return "register";
    }

    @RequestMapping("/forgotpass")
    public String gotoForgotpass() {
        return "forgotpass";
    }

    @RequestMapping({ "/userHome"})
    public String userHome(HttpSession session) {
            return "userHome";
    }

    @RequestMapping("/adminHome")
    public String adminHome(HttpSession session) {
            return "adminHome";
    }
    @RequestMapping(path = "/Signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute("user") UserBean user, BindingResult bindingResult, HttpSession session,
                         @RequestParam("aid") String[] id,
                         @RequestParam("profileimage") MultipartFile profilepic, Model model) throws IOException {

        int usertypeforedit = 0;
        if (bindingResult.hasErrors()) {
            List<FieldError> error = bindingResult.getFieldErrors();
            System.out.println(error);
            List<String> errorList = new ArrayList();
            for (FieldError err : error) {
                System.out.println("came if backend fail");
                errorList.add(err.getDefaultMessage());
                model.addAttribute("error", errorList);
                model.addAttribute("user", user);
            }
            return "register";
        }

        session.setAttribute("user", user);
        String profile = null;
        profile = Base64.getEncoder().encodeToString(profilepic.getBytes());
        
        if (profile != null) {
            user.setProfilepic(profile);
        }
        	
        System.out.println("userid:"+ user.getId());
        if (user.getId() != 0) {
            usertypeforedit = (int) session.getAttribute("role");

            List<AddressBean> addresses = user.getAddresses();
            for (int i = 0; i < id.length; i++) {
                if (!id[i].isEmpty()) {
                    int Addressid = Integer.parseInt(id[i]);
                    addresses.get(i).setAid(Addressid);

                }
            }
            System.err.println("Update User");  
            for (AddressBean address : user.getAddresses()) {
                address.setUserBean(user);
            }
            for (UserRoles roles : user.getRoles()) {
                roles.setUser(user);
            }
            signupServiceImpl.updateUser(user);

        } else {
            signupServiceImpl.addNewUser(user);
        }
        if (usertypeforedit == 0) {
            session.setAttribute("role", 0);
            System.out.println("user Logged in");
            return "redirect:userHome";
        } else if (usertypeforedit == 2) {
            System.out.println("Admin Logged in");
            //log.info("Admin Logged in");
            return "redirect:adminHome";

        }
        else {
        return "redirect:register";
        }
    }

//    @RequestMapping(path = "/Login", method = RequestMethod.POST)
//    public String login(@RequestParam String email, @RequestParam String pass, HttpSession session, Model model) {
//        int usertype = signupServiceImpl.checkUser(email, pass);
//        String noUser = "noUser";
//        session.setAttribute("email", email);
//        if (usertype == 1) {
//            session.setAttribute("role", 0);
//            return "redirect:userHome";
//
//        } else if (usertype == 2) {
//            session.setAttribute("role", 2);
//            return "redirect:adminHome";
//
//        } else {
//            model.addAttribute(noUser, "User Doesn't Exist");
//            return "index";
//        }
//    }

    @RequestMapping(path = "/UsersDetails", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String getUserDetails() throws Exception {

        ArrayList<UserBean> userDetails = signupServiceImpl.getUserDetails();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        String JSONObject = gson.toJson(userDetails);
        return JSONObject;
    }

    @RequestMapping(path = "/DeleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestParam int userId) {
        try {
            signupServiceImpl.deleteUser(userId);
        } catch (SecurityException | RollbackException | HeuristicMixedException | HeuristicRollbackException
                | SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/EditDetails")
    public String editDetails(HttpSession session) {

        if (session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
            UserBean userDetails = signupServiceImpl.getLoggedinUserDetails(email);
            
            session.setAttribute("user", userDetails);
        }
        return "register";
    }
    
    @RequestMapping(path = "/ForgotPass", method = RequestMethod.POST)
    public String forgotPass(@RequestParam String dob, @RequestParam String securityAns, HttpSession session,
            Model model) {
        int userId = signupServiceImpl.checkForgotpassDetails(dob, securityAns);
        System.out.println("valid:"+ userId);
        String didntmatch = "didntmatch";
        if (userId != 0) {
        session.setAttribute("userIdForPassChange", userId);
            return "changePass";
        } else {
            model.addAttribute(didntmatch, "User Doesn't Exist");
            return "forgotpass";
        }
    }
    
    @RequestMapping(path = "/ChangePass", method = RequestMethod.POST)
    public String changePass(@RequestParam String newPass, HttpSession session) {

        int userId = (int) session.getAttribute("userIdForPassChange");
        signupServiceImpl.changePass(userId,newPass);

        return "index";

    }
    
//    @RequestMapping("/Logout")
//    public String logOut(HttpSession session) {
//        session.invalidate();
//        return "redirect:index";
//    }

    @RequestMapping("/emailFromUser")
    public ModelAndView emailFromUser(@RequestParam("email") String userEmail, HttpSession session) {
        session.setAttribute("email", userEmail);
        return new ModelAndView("redirect:/EditDetails");
    }

    @RequestMapping(path = "/torecentlyregistered")
    public String torecentlyregistered() {
        return "recentlyRegisteredUsers";
    }

    @RequestMapping(path = "/getRecentUsers", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String getRecentUsers() {
        ArrayList<UserBean> recentUsers = signupServiceImpl.getRecentUsersList();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        String JSONObject = gson.toJson(recentUsers);
        return JSONObject;

    }
    
    @RequestMapping("/CheckEmailAvailability")
    @ResponseBody
    public String checkEmailAvailability(@RequestParam("email") String email, HttpSession session) {
        String emailExists = signupServiceImpl.checkEmail(email);
        if (session.getAttribute("email") == null) {
            return emailExists;
        } else {
            return emailExists;
        }
    }
}
