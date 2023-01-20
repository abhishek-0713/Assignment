package com.platformcommons.repository;

import com.platformcommons.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

	@Query("FROM Admin c WHERE c.mobileNumber=?1")
	public List<Admin> findAdminByMobile(String mobileNumber);
	
}