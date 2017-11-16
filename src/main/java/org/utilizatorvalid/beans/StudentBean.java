package org.utilizatorvalid.beans;

import org.utilizatorvalid.services.StudentService;
import org.utilizatorvalid.entities.Student;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "studentBean")
@ViewScoped
public class StudentBean implements DataBeanInterface {

    private final static Long serialVersionUID = 1L;

    private StudentService studentService;
    private List<Student> students;


    @PostConstruct
    public void init() {
        System.out.println("read from db");
        this.studentService = StudentService.getInstance();
        this.students = studentService.getStudents();
        Student.header = new ArrayList<>();
        Student.header.add("id");
        Student.header.add("name");
        Student.header.add("email");
    }



    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public List getElements() throws Exception {
        return getStudents();
    }

    @Override
    public List<String> getHeader() {
        return Student.header;
    }
}
