package org.utilizatorvalid.DAO;

import org.utilizatorvalid.entities.Skill;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SkillDAO {

    void addSkill(Skill skill) throws SQLException;

    List<Skill> getSkills() throws SQLException;

    Optional<Skill> getSkill(int id) throws SQLException;
    void deleteSkill(Skill skill) throws SQLException;

}
