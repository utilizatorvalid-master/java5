package org.utilizatorvalid.DAO;

import org.utilizatorvalid.entities.Skill;
import org.utilizatorvalid.utils.ConnectionUtil;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillDAOImpl implements SkillDAO, Serializable {

    private final static Long serialVersionUID = 1L;

    private Connection connection;

    private final static String ADD_SKILL = "INSERT INTO SKILLS (name) VALUES(?)";
    private final static String GET_SKILLS = "SELECT * FROM SKILLS";
    private final static String GET_SKILL_BY_ID = "SELECT * FROM SKILLS WHERE ID=?";
    private final static String DELETE_SKILL = "DELETE FROM SKILLS WHERE ID=?";

    @Override
    public void deleteSkill(Skill skill) throws SQLException {
        PreparedStatement p = connection.prepareStatement(DELETE_SKILL);
        p.setInt(1, skill.getId());
        int affectedRows = p.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Failed to delete the skill with id:" + skill.getId());
        }
    }


    public SkillDAOImpl() {
        this.connection = ConnectionUtil.getInstance().connection;
    }


    @Override
    public void addSkill(Skill skill) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(ADD_SKILL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, skill.getName());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Failed to insert new Skill");
        }

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            skill.setId(generatedKeys.getInt(1));
        } else {
            throw new SQLException("Failed to retrieve the id of the new Skill");
        }
    }

    @Override
    public List<Skill> getSkills() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_SKILLS);
        List<Skill> skills = new ArrayList<>();
        while (resultSet.next()) {
            Skill skill = new Skill();
            skill.setId(resultSet.getInt("id"));
            skill.setName(resultSet.getString("name"));
            skills.add(skill);
        }
        return skills;

    }

    @Override
    public Optional<Skill> getSkill(int id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(GET_SKILL_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next() == false) {
            return Optional.empty();
        }
        Skill skill = new Skill();
        skill.setId(resultSet.getInt("id"));
        skill.setName(resultSet.getString("name"));
        return Optional.of(skill);
    }
}
