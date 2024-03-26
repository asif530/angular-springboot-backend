package com.docker.backend.CommandLiner;

import com.docker.backend.Dto.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CommandLineAppStartupRunner  implements CommandLineRunner {
    public static HashMap<Long, Employee> employeeHashMap = new HashMap<>();


    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee();
        for (int i=1;i<=10;i++){
            Employee employee1 = new Employee(Integer.toUnsignedLong(i),"employee"+i,Integer.toUnsignedLong(i+20));
            employeeHashMap.put(Integer.toUnsignedLong(i),employee1);
        }
    }
}
