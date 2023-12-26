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
    public int getTotalFinancialGoalValuesOfShortTermGoals() {
        int sum = 0;
        for (Goal goal : goals) {
            if (goal.getGoalTerm()!=null &&"Short Term".equals(goal.getGoalTerm()) && goal.getFinancialGoalValue() != null) {
                sum += goal.getFinancialGoalValue();
            }
        }
        return sum;
    }
    public int getTotalFinancialGoalValuesOfMidTermGoals() {
        int sum = 0;
        for (Goal goal : goals) {
            if (goal.getGoalTerm()!=null &&"Mid Term".equals(goal.getGoalTerm()) && goal.getFinancialGoalValue() != null) {
                sum += goal.getFinancialGoalValue();
            }
        }
        return sum;
    }
    public int getTotalFinancialGoalValuesOfLongTermGoals() {
        int sum = 0;
        for (Goal goal : goals) {
            if (goal.getGoalTerm()!=null &&"Long Term".equals(goal.getGoalTerm()) && goal.getFinancialGoalValue() != null) {
                sum += goal.getFinancialGoalValue();
            }
        }
        return sum;
    }
    public int getTotalAmountOfShortTermGoals() {
        int sum = 0;
        for (Goal goal : goals) {
            if (goal.getGoalTerm()!=null &&"Short Term".equals(goal.getGoalTerm()) && goal.getTotalAmount() != null) {
                sum += goal.getTotalAmount();
            }
        }
        return sum;
    }
    public int getTotalAmountOfMidTermGoals() {
        int sum = 0;
        for (Goal goal : goals) {
            if (goal.getGoalTerm()!=null &&"Mid Term".equals(goal.getGoalTerm()) && goal.getTotalAmount() != null) {
                sum += goal.getTotalAmount();
            }
        }
        return sum;
    }
    public int getTotalAmountOfLongTermGoals() {
        int sum = 0;
        for (Goal goal : goals) {
            if (goal.getGoalTerm()!=null &&"Long Term".equals(goal.getGoalTerm()) && goal.getTotalAmount() != null) {
                sum += goal.getTotalAmount();
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        return "UserGoal [id=" + id + ", user=" + user + ", goals=" + goals + "]";
    }
}
