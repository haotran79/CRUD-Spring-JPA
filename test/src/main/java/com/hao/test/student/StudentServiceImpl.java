package com.hao.test.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Integer id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Integer id) {
		studentRepository.deleteById(id);
	}

	@Override
	public boolean checkStudent(Student student) {
		if(student.getMajor().equals("")
				|| student.getName().equals("") 
				|| student.getDob().equals("") || student.getDob().length() > 10) {
			return false;
		} else {
			List<Student> students = studentRepository.findByFullNameAndDateOfBirthAndIdNot(student.getName(), student.getDob(), student.getId());
			if(students.size() > 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void setError(Student student, Model model) {
		if (student.getMajor().equals("")) {
		    model.addAttribute("NullMajor", "true");
		}
		if(student.getName().equals("")) {
			model.addAttribute("NullName", "true");
		}
		if(student.getDob().equals("") || student.getDob().length() > 10) {
			model.addAttribute("NullDOB", "true");
		} else {
			List<Student> students = studentRepository.findByFullNameAndDateOfBirthAndIdNot(student.getName(), student.getDob(), student.getId());
			if(students.size() > 0) {
				model.addAttribute("error", "true");
			}
		}
	}
}
