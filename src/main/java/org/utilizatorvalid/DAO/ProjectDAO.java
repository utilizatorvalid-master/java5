package org.utilizatorvalid.DAO;

import org.utilizatorvalid.entities.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDAO {

    List<Project> getProjects();

    void addProject(Project project) throws SQLException;

    void deleteProject(Project project) throws SQLException;
}
