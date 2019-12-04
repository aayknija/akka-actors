package com.actors.actor;

import akka.actor.AbstractActor;
import com.actors.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmployeeActor extends AbstractActor {

    @Autowired
    private EmployeeRepository employeeRepository;

   /* private final PartialFunction<Object, BoxedUnit> create;
    private final PartialFunction<Object, BoxedUnit> update;*/

    @Data
    public static final class CreateEmployee implements Serializable {
        private final Employee employee;
        public CreateEmployee(Employee employee){
            this.employee=employee;
        }
    }
    @Data
    public static final class UpdateEmployee implements Serializable {
        private final Employee employee;
        public UpdateEmployee(Employee employee){
            this.employee=employee;
        }
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static final class Employee implements Serializable {
        @Id
        private String id;
        private String firstName;
        private String lastName;
        private String oldName;
        private String type;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CreateEmployee.class,
                        employee -> addEmployee(employee.getEmployee()))
                .match(UpdateEmployee.class, employee -> updateEmployee(employee.getEmployee()))
                .build();
    }

    private Employee updateEmployee(Employee employee) {
        List<Employee> dbEmployees = employeeRepository.findByFirstName(employee.getFirstName());
        if(!CollectionUtils.isEmpty(dbEmployees)){
            dbEmployees.stream().forEach(dbEmployee -> {
                dbEmployee.setOldName(dbEmployee.getFirstName() + "" + dbEmployee.getLastName());
                dbEmployee.setFirstName(employee.getFirstName());
                dbEmployee.setLastName(employee.getLastName());
                employeeRepository.save(dbEmployee);
            });
        } else {
            employeeRepository.save(employee);
        }

        return employee;
    }

    private Employee addEmployee(Employee employee){
        employeeRepository.save(employee);
        return employee;
    }
}
