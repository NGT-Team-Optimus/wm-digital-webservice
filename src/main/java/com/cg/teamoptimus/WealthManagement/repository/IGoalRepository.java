package com.cg.teamoptimus.WealthManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.cg.teamoptimus.WealthManagement.model.Goal;

@Repository
public interface IGoalRepository extends MongoRepository<Goal,Integer> {
  //  @Query("select goals from  where app.doctor.doctorId = ?1")
    //List<Goal> findGoalByUser(String userId);
	

}
