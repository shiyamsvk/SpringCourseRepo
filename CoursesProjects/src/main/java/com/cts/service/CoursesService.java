package com.cts.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Exception.CoursesNotFoundException;
import com.cts.model.Courses;
import com.cts.repository.CoursesRepostory;




@Service
public class CoursesService {

	@Autowired
	CoursesRepostory coursesRepostory;
	
	@Transactional
	public List<Courses> getAllCourses() {

		return this.coursesRepostory.findAll();
	}
	
	@Transactional
	public Courses getCoursesByCoursesName( String createdby) {

		return coursesRepostory.getById(createdby);
	}

	@Transactional
	public void editCourses(Courses courses) throws CoursesNotFoundException {
		
		//System.out.println("-------------- first"+courses.getTeacher_Name());
		Courses courses2=this.coursesBycourseId(courses.getCourseId());
		//System.out.println("-------------- sec"+courses.getTeacher_Name());
		courses2.setCourse_Name(courses.getCourse_Name());
		  courses2.setClass1(courses.getClass1());
		  courses2.setTeacher_Name(courses.getTeacher_Name());
		  courses2.setCreatedby(courses.getCreatedby());
		  courses2.setModifiedby(courses.getModifiedby());
		  courses2.setCreated_date(courses.getModified_date());
		  courses2.setCreated_date(courses.getCreated_date());
		 coursesRepostory.save(courses2);
	}

	@Transactional
	public void addDetails(@Valid Courses courses) {
		// TODO Auto-generated method stub
		coursesRepostory.save(courses);
	}

	
	public Courses coursesBycourseId(int courseNumber) throws CoursesNotFoundException {
		Optional<Courses> optional = coursesRepostory.findById(courseNumber);
		if (!optional.isPresent())
			throw new CoursesNotFoundException();

		return optional.get();

	}
}
