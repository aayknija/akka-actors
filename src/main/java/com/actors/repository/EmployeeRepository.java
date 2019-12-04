package com.actors.repository;

import com.actors.actor.EmployeeActor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeActor.Employee,String> {
    List<EmployeeActor.Employee> findByFirstName(String firstName);
}
