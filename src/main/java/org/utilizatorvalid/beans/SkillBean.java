package org.utilizatorvalid.beans;

import org.utilizatorvalid.entities.Project;
import org.utilizatorvalid.entities.Skill;
import org.utilizatorvalid.services.SkillService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "skillBean")
@ViewScoped
public class SkillBean implements DataBeanInterface {

    private final static Long serialVersionUID = 1L;

    private SkillService skillService;
    private List<Skill> skills;
    private Skill selectedSkill;
    private Skill newSkill;

    @PostConstruct
    public void init() {
        skillService = SkillService.getInstance();
        skills = skillService.getSkills();
        newSkill = new Skill();

        Skill.header = new ArrayList<>();
        Skill.header.add("id");
        Skill.header.add("name");
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Skill getSelectedSkill() {
        return selectedSkill;
    }

    public void setSelectedSkill(Skill selectedSkill) {
        this.selectedSkill = selectedSkill;
    }

    public Skill getNewSkill() {
        return newSkill;
    }

    public void setNewSkill(Skill newSkill) {
        this.newSkill = newSkill;
    }

    public void addSkill() {

        System.out.println("Add skill:" + newSkill.toString());

        boolean success = skillService.addSkill(newSkill);
        RequestContext context = RequestContext.getCurrentInstance();
        if (success) {
            skills.add(newSkill);
            addMessage("Skill added", FacesMessage.SEVERITY_INFO);
            context.addCallbackParam("success", true);
        } else {
            addMessage("Failed to add the skill", FacesMessage.SEVERITY_ERROR);
            newSkill = new Skill();
            context.addCallbackParam("success", false);
        }
    }

    public void deleteSkill() {
        System.out.println("delete skill " + selectedSkill);
        boolean success = skillService.deleteSkill(selectedSkill);
        if(success){
            skills.remove(selectedSkill);
            addMessage("Skill removed", FacesMessage.SEVERITY_INFO);

        }else{
            addMessage("Filed to delete the skill", FacesMessage.SEVERITY_ERROR);
        }
        selectedSkill = null;
    }

    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public List getElements() throws Exception {
        return getSkills();
    }

    @Override
    public List<String> getHeader() {
        return Skill.header;
    }
}
