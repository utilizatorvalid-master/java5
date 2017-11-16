package org.utilizatorvalid.services;

import org.utilizatorvalid.DAO.ProjectDAO;
import org.utilizatorvalid.DAO.ProjectDAOImpl;
import org.utilizatorvalid.entities.Project;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {

    private static ProjectService INSTANCE;

    private ProjectDAO projectDAO;

    private ProjectService() {
        this.projectDAO = new ProjectDAOImpl();
    }

    public synchronized static ProjectService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProjectService();
        }
        return INSTANCE;
    }


    public List<Project> getProjects() {
        return projectDAO.getProjects();
    }

    public boolean addProject(Project project) {
        try {
            projectDAO.addProject(project);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteProject(Project project) {
        try {
            projectDAO.deleteProject(project);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
