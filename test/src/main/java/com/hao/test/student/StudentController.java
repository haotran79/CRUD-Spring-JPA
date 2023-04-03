package com.hao.test.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	// get all students ở đây
	@GetMapping("/students2")
	public String getStudents(Model model){
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("student/{id}")
	public String editStudentForm(@PathVariable Integer id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit-student";
	}
	
	@PutMapping("student/save/{id}")
	public String updateStudent(Student student, @PathVariable Integer id, Model model) {
		if(!studentService.checkStudent(student) || student.getDob().equals("")) {
			studentService.setError(student, model);
			return "edit-student";
		}
		// save updated student object
		studentService.updateStudent(student);
		return "redirect:/students2";		
	}
	
	@GetMapping("student/new")
	public String newStudentForm(Model model) {
		Student student =  new Student();
		model.addAttribute("student", student);
		return "new-student";
	}
	
	@PostMapping("student/save")
	public String addStudent(Student student, Model model) {
		if(!studentService.checkStudent(student)) {
			studentService.setError(student, model);
			return "new-student";
		}
		studentService.saveStudent(student);
		return "redirect:/students2";
	}
	
	@DeleteMapping("student/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		studentService.deleteStudentById(id);
		return "redirect:/students2";
	}
}
