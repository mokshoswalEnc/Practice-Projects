package com.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fs.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
