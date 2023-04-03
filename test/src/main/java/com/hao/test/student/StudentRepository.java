package com.hao.test.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	@Query("SELECT s FROM Student s WHERE s.name = :name AND s.dob = :dob AND s.id != :id")
    List<Student> findByFullNameAndDateOfBirthAndIdNot(@Param("name") String name, @Param("dob") String dob, @Param("id") Integer id);
}
