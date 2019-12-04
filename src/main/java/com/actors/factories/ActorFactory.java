package com.actors.factories;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.actors.actor.EmployeeActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.actors.config.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Component
public class ActorFactory {

    private ActorRef employeeActor;

    @Autowired
    private ActorSystem system;

    public ActorRef getEmployeeActor(){
        if(employeeActor == null){
            employeeActor = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system).props("employeeActor"), "employee");
        }
        return employeeActor;
    }

}
