package org.utilizatorvalid.services;

import org.utilizatorvalid.DAO.StudentDAOImpl;
import org.utilizatorvalid.DAO.StudentDAO;
import org.utilizatorvalid.entities.Student;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class StudentService {

    private static StudentService INSTANCE;

    private StudentDAO studentDAO;

    private StudentService() {
        this.studentDAO = new StudentDAOImpl();
    }

    public synchronized static StudentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StudentService();
        }
        return INSTANCE;
    }

    public List<Student> getStudents() {
        try {
            List<Student> students = studentDAO.getStudents();
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public boolean addStudent(Student student) {
        try {
            studentDAO.addStudent(student);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteStudent(Student student){
        try{
            this.studentDAO.deleteStudent(student);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
