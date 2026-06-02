package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybage.model.Complaint;
import com.cybage.model.Likes;
import com.cybage.model.User;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Integer> {
Likes findLikeByUserIdAndComplaintId(User user, Complaint complaint);

List<Likes> findAllByComplaintId(Complaint complaint);
/*@Query("select count(*) from Likes l where l.complaintId=:complaintId")
int countLikesByComplaintId(@Param("complaintId") int complaintId);*/

List<Likes> findByComplaintId(Complaint complaint);

@Query(value = "SELECT Count(*) FROM like_table WHERE complaint_id = ?1 group by complaint_id", nativeQuery = true)
int findLikeCountByComplaintNo(Integer  complaintId);
}
