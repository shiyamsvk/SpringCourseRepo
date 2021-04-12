package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.model.Courses;

@Repository
public interface CoursesRepostory extends JpaRepository<Courses, Integer> {


	@Query("select m from Courses m where m.course_Name = :course_Name")
    Courses getById(String course_Name);

	Courses coursesBycourseId(int courseId);

	

}
