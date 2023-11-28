package com.cg.teamoptimus.WealthManagement.model;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "goals")
public class Goal {
    @Id
    private int goalId;
    private String goalName;
    private Date duration;
    private Long financialGoalValue;
    private String status; 
    private List<Transaction> transactions;
    private Long totalAmount;
	private String goalTerm;
	private boolean isFirstTimeDurationSet = true;
	
	

	public Goal(int goalId, String goalName, Date duration, Long financialGoalValue, String status,
			List<Transaction> transactions, Long totalAmount, String goalTerm, boolean isFirstTimeDurationSet) {
		super();
		this.goalId = goalId;
		this.goalName = goalName;
		this.duration = duration;
		this.financialGoalValue = financialGoalValue;
		this.status = status;
		this.transactions = transactions;
		
		this.totalAmount = totalAmount;
		this.goalTerm = goalTerm;
		this.isFirstTimeDurationSet = isFirstTimeDurationSet;
	}
	public Goal() {

	}


	public int getGoalId() {
		return goalId;
	}
	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}
	public String getGoalName() {
		return goalName;
	}
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	
	public Date getDuration() {
		return duration;
	}
	public void setDuration(Date duration) {
		if (isFirstTimeDurationSet && duration != null) {
			this.duration = duration;
			setGoalTerm(goalTerm);
			isFirstTimeDurationSet = false;
		} else {
			this.duration = duration;
		}

	}
    public Long getFinancialGoalValue() {
        return financialGoalValue;
    }

    public void setFinancialGoalValue(Long financialGoalValue) {
        this.financialGoalValue = financialGoalValue;
    }
    
	public String isStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	
	
	public String getStatus() {
		return status;
	}



	public String getGoalTerm() {
		return goalTerm;
	}

	private void setGoalTerm(String goalTerm) {
		if (duration != null) {
			Date currentDate = new Date();
			long durationInMillis = duration.getTime() - currentDate.getTime();
			long months = Duration.ofMillis(durationInMillis).toDays() / 30;
			if (months < 3) {
				this.goalTerm = "Short Term";
			} else if (months >= 3 && months < 12) {
				this.goalTerm = "Mid Term";
			} else {
				this.goalTerm = "Long Term";
			}
		} else {
			this.goalTerm = "null";
		}
	}

	public boolean isFirstTimeDurationSet() {
		return isFirstTimeDurationSet;
	}

	public void setFirstTimeDurationSet(boolean firstTimeDurationSet) {
		isFirstTimeDurationSet = firstTimeDurationSet;
	}

	@Override
	public String toString() {
		return "Goal [goalId=" + goalId + ", goalName=" + goalName + ", duration=" + duration + ", financialGoalValue="
				+ financialGoalValue + ", status=" + status + ", transactions=" + transactions + ", totalAmount="
				+ totalAmount + ", goalTerm=" + goalTerm + ", isFirstTimeDurationSet=" + isFirstTimeDurationSet + "]";
	}



	
	



}
