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
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    private StudentService studentService;
    private List<Student> students;
    private Student newStudent;
    private Student selectedStudent;


    @PostConstruct
    public void init() {
        System.out.println("read from db");
        this.studentService = StudentService.getInstance();
        this.students = studentService.getStudents();
        this.newStudent = new Student();
        Student.header = new ArrayList<>();
        Student.header.add("id");
        Student.header.add("name");
        Student.header.add("email");
    }

    public void addStudent(ActionEvent event) {
        System.out.println("Adding student:" + newStudent.toString());
        RequestContext context = RequestContext.getCurrentInstance();


        boolean success = studentService.addStudent(newStudent);
        if (success) {
            context.addCallbackParam("success", true);
            this.addMessage("A new Student are created", FacesMessage.SEVERITY_INFO);

            students.add(newStudent);
            newStudent = new Student();
        } else {
            this.addMessage("Some error while creating Student", FacesMessage.SEVERITY_ERROR);
            context.addCallbackParam("success", false);
        }


    }

    private void addMessage(String info, FacesMessage.Severity severity){
        FacesMessage message = new FacesMessage(severity, info, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void deleteStudent(ActionEvent event) {
        System.out.println("Deleting student:" + selectedStudent);
        boolean success = studentService.deleteStudent(selectedStudent);
        FacesContext context = FacesContext.getCurrentInstance();
        if (success) {
            this.addMessage("Student deleted", FacesMessage.SEVERITY_INFO);
            students.remove(selectedStudent);
        } else {
            this.addMessage("Some error while deleting Student", FacesMessage.SEVERITY_ERROR);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
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
