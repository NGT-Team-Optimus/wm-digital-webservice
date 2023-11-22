package com.cg.teamoptimus.WealthManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "user-goals")
public class UserGoal {

    @Id
    private String id;
    private User user;
    
    private List<Goal> goals; //All goals

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public UserGoal(User user, List<Goal> goals) {
        this.user = user;
        this.goals = goals;
    }
    public int getNumberOfGoals() {
        return goals.size();
    }
    
    

    @Override
    public String toString() {
        return "UserGoal [id=" + id + ", user=" + user + ", goals=" + goals + "]";
    }
}
