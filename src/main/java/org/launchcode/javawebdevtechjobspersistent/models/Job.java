package org.launchcode.javawebdevtechjobspersistent.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

    @JsonManagedReference
    @ManyToOne
    private Employer employer;

    @JsonManagedReference
    @ManyToMany
    @NotNull(message = "Must include skills!")
    private List<Skill> skills;

    public Job() {}

    // Getters and setters.

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
