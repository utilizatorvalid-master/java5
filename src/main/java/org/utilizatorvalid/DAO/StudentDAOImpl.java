package org.utilizatorvalid.DAO;

import org.utilizatorvalid.entities.Student;
import org.utilizatorvalid.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {


    private Connection connection;
    private final static String GET_STUDENTS = "SELECT * FROM Students";
    private final static String ADD_STUDENT = "INSERT INTO Students(name,email) VALUES(?,?)";
    private final static String DELETE_STUDENT = "DELETE FROM STUDENTS WHERE ID=?";

    public StudentDAOImpl() {
        this.connection = ConnectionUtil.getInstance().connection;
    }

    @Override
    public List<Student> getStudents() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_STUDENTS);
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setEmail(resultSet.getString("email"));
            students.add(student);
            System.out.println(student);
        }

        return students;
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(ADD_STUDENT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, student.getName());
        statement.setString(2, student.getEmail());
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Filed to add new Student");
        }
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            student.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("Failed to retrieve the if of the new project");
        }
    }

    @Override
    public void deleteStudent(Student student) throws SQLException {
        PreparedStatement p = connection.prepareStatement(DELETE_STUDENT);
        p.setInt(1, student.getId());
        int affectedRows = p.executeUpdate();
        if (affectedRows == 0) {
           throw new SQLException("Failed to delete the student with id:"+ student.getId());
        }
    }



}
