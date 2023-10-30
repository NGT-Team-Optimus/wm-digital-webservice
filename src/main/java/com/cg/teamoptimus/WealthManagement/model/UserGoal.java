package com.cg.teamoptimus.WealthManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "user-goals")
public class UserGoal {

    @Id
    private UUID id;

    @DBRef
    private User user;

    @DBRef
    private List<Goal> goals;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public UserGoal(UUID id, User user, List<Goal> goals) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.goals = goals;
    }
}
