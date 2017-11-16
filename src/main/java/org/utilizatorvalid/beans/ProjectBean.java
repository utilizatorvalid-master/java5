package org.utilizatorvalid.beans;

import org.utilizatorvalid.entities.Project;
import org.utilizatorvalid.services.ProjectService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "projectBean")
public class ProjectBean implements DataBeanInterface{

    private ProjectService projectService;

    private List<Project> projects;
    private Project selectedProject;
    private Project newProject;

    @PostConstruct
    public void init() {
        this.projectService = ProjectService.getInstance();
        this.projects = projectService.getProjects();
        this.newProject = new Project();
        Project.header = new ArrayList<>();
        Project.header.add("id");
        Project.header.add("name");
        Project.header.add("description");
        Project.header.add("capacity");

    }

    public void removeProject(ActionEvent event) {

        System.out.printf("Remove the project with id:%d\n", selectedProject.getId());
        boolean projectWasRemoved = projectService.deleteProject(selectedProject);
        if (projectWasRemoved) {
            addMessage("Project was removed", FacesMessage.SEVERITY_INFO);
            projects.remove(selectedProject);
        } else {
            addMessage("Some error occurred during the removal of the project", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addProject() {
        System.out.println("Add project:" + newProject.toString());

        boolean projectWasAdded = projectService.addProject(newProject);
        RequestContext context = RequestContext.getCurrentInstance();
        if (projectWasAdded) {
            context.addCallbackParam("success", true);
            addMessage("Project was added", FacesMessage.SEVERITY_INFO);
            projects.add(newProject);
            newProject = new Project();
        } else {
            context.addCallbackParam("success", false);
            addMessage("Some error occured during insertion of the project", FacesMessage.SEVERITY_ERROR);
        }
    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }


    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public Project getNewProject() {
        return newProject;
    }

    public void setNewProject(Project newProject) {
        this.newProject = newProject;
    }

    @Override
    public List getElements() throws Exception {
        return getProjects();
    }

    @Override
    public List<String> getHeader() {
        return Project.header;
    }
}
