package org.utilizatorvalid.DAO;

import org.utilizatorvalid.entities.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    List<Student> getStudents() throws SQLException;

    void addStudent(Student student) throws SQLException;
    void deleteStudent(Student student) throws SQLException;
}
