package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.Status;


@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
	
    List<Complaint> findComplaintByStatus(Status status);
	List<Complaint> findComplaintByDepartmentId(Department departmentId);
	List<Complaint> findComplaintByComplaintReminderAndDepartmentId(boolean complaintReminder,Department departmentId);
	
	@Query("SELECT c FROM Complaint c WHERE c.userId.userId = :id")
    List<Complaint> getComplaintsByUserId(@Param("id") Integer id);
	
    @Modifying
    @Query("UPDATE Complaint c SET c.complaintReminder = true WHERE c.complaintId = :id")
    void enableComlaintReminder(@Param("id") Integer id);
    
    @Modifying
    @Query("UPDATE Complaint c SET c.complaintReminder = false WHERE c.complaintId = :id")
    void dissableComplaintReminder(@Param("id") Integer id);
    
    @Query("SELECT c.status FROM Complaint c WHERE c.userId.userId = :id")
    String getStatusById(@Param ("id") Integer id);
    
    @Modifying
    @Query("UPDATE Complaint c SET c.status= :Status WHERE c.complaintId = :id")
    int reopenComplaint(@Param("Status") String Status, @Param("id")Integer id );
    
    @Modifying
    @Query("UPDATE Complaint c SET c.likeCount = :like WHERE c.complaintId = :id")
    void likeComplaint(@Param("like") Integer like, @Param("id") Integer id);
    
    @Modifying
    @Query("UPDATE Complaint c SET c.status= :Status WHERE c.complaintId = :id")
    void changeStatusReopen(@Param("Status") Status Status, @Param("id")Integer id );
    
    //getStatusByComplaintID
    @Query("SELECT c.status FROM Complaint c WHERE c.complaintId = :id")
    String getStatusByComplaintId(@Param ("id") Integer id);
    
    //Like
    
    @Query("SELECT l.likeCount FROM Complaint l Where l.complaintId = :id")
    int getLikeCount(@Param("id") Integer id);
    
}
