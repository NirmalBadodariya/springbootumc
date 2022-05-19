package com.example.springbootumc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "users")
public class UserBean {
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Expose
    @Size(min = 2, max = 30)
    @NotBlank(message = "FirstName Cannot be blank")
    private String firstName;
    @NotBlank(message = "LastName Cannot be blank")
    @Expose
    private String lastName;
    @Expose
    @NotEmpty
    @NotBlank(message = "email Cannot be blank")
    @Email(message = "Please enter valid email")
    private String email;
    @Expose
    @NotBlank(message = "phone Cannot be blank")
    private String phone;
    @Expose
    private String gender;
    @Expose
    private String dob;
    @Expose
    private String pass;
    @Expose

    private String securityAns;
    @Lob
    @Expose
    @Column(nullable = true)
    private String profilepic;

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getSecurityAns() {
        return securityAns;
    }

    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }

    // private String security_ans;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressBean> addresses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRoles> roles;

    public List<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles;
    }

    public List<AddressBean> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressBean> addresses) {
        this.addresses = addresses;
    }

    // private InputStream image;
    // private String base64Image;

    // public String getBase64Image() {
    // return base64Image;
    // }

    // public void setBase64Image(String base64Image) {
    // this.base64Image = base64Image;
    // }
    // public InputStream getImage() {
    // return image;
    // }

    // public void setImage(InputStream image) {
    // this.image = image;
    // }

    @Override
    public String toString() {
        return "UserBean [addresses=" + addresses + ", dob=" + dob + ", email=" + email + ", firstName=" + firstName
                + ", gender=" + gender + ", id=" + id + ", lastName=" + lastName + ", pass=" + pass + ", phone=" + phone
                + ", roles=" + roles + ", securityAns=" + securityAns + "]";
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    // private String user_profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    // public String getUser_profile() {
    // return user_profile;
    // }

    // public void setUser_profile(String user_profile) {
    // this.user_profile = user_profile;
    // }

}
