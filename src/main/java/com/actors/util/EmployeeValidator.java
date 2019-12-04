package com.actors.util;

import com.actors.actor.EmployeeActor;
import org.springframework.util.StringUtils;

public class EmployeeValidator {

    public static void validate(EmployeeActor.Employee employee) throws Exception{
       if( StringUtils.isEmpty(employee.getFirstName()) ||  StringUtils.isEmpty(employee.getLastName())){
            throw new Exception("Name can not be empty");
       }
    }
}
