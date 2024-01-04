package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.task;
@Repository

public interface taskrepository extends JpaRepository<task,Integer> {
	public List<task>findAll();
	public abstract void deleteById(int id);

}
