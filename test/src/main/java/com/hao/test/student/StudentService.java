package com.hao.test.student;

import java.util.List;

import org.springframework.ui.Model;

public interface StudentService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Integer id);
	
	Student updateStudent(Student student);
	
	void deleteStudentById(Integer id);
	
	boolean checkStudent(Student student);
	
	void setError(Student student, Model model);
}
