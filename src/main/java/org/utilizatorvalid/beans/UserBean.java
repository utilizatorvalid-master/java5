package org.utilizatorvalid.beans;

import org.utilizatorvalid.entities.User;
import org.utilizatorvalid.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable{
    private final static Long serialVersionUID = 1L;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    private UserService userService;
    @PostConstruct
    public void init() {
        System.out.println("UserBean Init");
        this.userService= UserService.getInstance();
        this.user = new User();

    }

    public  String doLogin(){
        System.out.println("do login"+ this.user.toString());

        boolean correctCredentials = this.userService.validate(this.user);
        System.out.println(correctCredentials);
        if(correctCredentials){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("user", this.user);
            return "goHome";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "goToLogin";
        }
    }
    public String doLogout() {
        System.out.println("aici");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.invalidate();
        return "goToLogin";
    }

}
