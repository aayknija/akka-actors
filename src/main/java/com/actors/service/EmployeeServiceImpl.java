package com.actors.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import com.actors.actor.EmployeeActor;
import com.actors.factories.ActorFactory;
import com.actors.util.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ActorFactory actorFactory;

    @Override
    public void uploadEmployees(String fileURL) throws Exception {
        ActorRef employeeActor = actorFactory.getEmployeeActor();
        List<EmployeeActor.Employee> employees = CSVUtils.read(EmployeeActor.Employee.class, new URL(fileURL).openStream());
        employees.stream()
                .forEach(employee -> employeeActor.tell(new EmployeeActor.CreateEmployee(employee),ActorRef.noSender()));
        employeeActor.tell(PoisonPill.getInstance(),ActorRef.noSender());

    }

    @Override
    public void updateEmployees(String fileURL) throws Exception {
        ActorRef employeeActor = actorFactory.getEmployeeActor();
        List<EmployeeActor.Employee> employees = CSVUtils.read(EmployeeActor.Employee.class, new URL(fileURL).openStream());
        employees.stream()
                .forEach(employee -> employeeActor.tell(new EmployeeActor.UpdateEmployee(employee),ActorRef.noSender()));
        employeeActor.tell(PoisonPill.getInstance(),ActorRef.noSender());

    }
}
