package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.model.Complaint;
import com.cybage.model.Ratings;
import com.cybage.model.User;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, Integer> {
	List<Ratings> findAllByComplaintId(Complaint complaint);
	Ratings findByUserIdAndComplaintId(User user, Complaint complaint);
}
