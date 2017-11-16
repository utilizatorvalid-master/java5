package org.utilizatorvalid.beans;

import org.primefaces.context.RequestContext;
import org.utilizatorvalid.entities.Student;
import org.utilizatorvalid.services.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="crudStudentBean")
public class CrudStudentBean implements EditBeanInterface{
    private final static Long serialVersionUID = 1L;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private StudentService studentService;
    private Student entity;
    private Student selectedEntity;


    public Student getEntity() {
        return entity;
    }

    public void setEntity(Student entity) {
        this.entity = entity;
    }

    @PostConstruct
    public void init(){
        this.studentService = StudentService.getInstance();
        Student.header = new ArrayList<>();
        this.entity = new Student();
        Student.header.add("name");
        Student.header.add("email");
    }

    public String addEntity() {
        System.out.println("Adding student:" + entity.toString());
        RequestContext context = RequestContext.getCurrentInstance();


        boolean success = studentService.addStudent(entity);
        if (success) {
            context.addCallbackParam("success", true);
            this.addMessage("A new Student are created", FacesMessage.SEVERITY_INFO);
            return "true";
        } else {
            this.addMessage("Some error while creating Student", FacesMessage.SEVERITY_ERROR);
            context.addCallbackParam("success", false);
            return "false";
        }



    }
    public void deleteStudent(ActionEvent event) {
        System.out.println("Deleting student:" + selectedEntity);
        boolean success = studentService.deleteStudent(selectedEntity);
        FacesContext context = FacesContext.getCurrentInstance();
        if (success) {
            this.addMessage("Student deleted", FacesMessage.SEVERITY_INFO);
        } else {
            this.addMessage("Some error while deleting Student", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void addMessage(String info, FacesMessage.Severity severity){
        FacesMessage message = new FacesMessage(severity, info, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public List<String> getAttributes() {
        return Student.header;
    }
}
