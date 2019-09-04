package com.company.controller;

import com.company.grpc.EmployeeCompanyClient;
import com.company.grpc.employeecompany.Company;
import com.company.model.Employee;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeCompanyController {
    @Autowired
    private EmployeeCompanyClient employeeCompanyClient;

    @PostMapping("/produce")
    public Company sendCompany(@RequestBody Employee employee)
    {
        com.company.grpc.employeecompany.Employee employee1 = com.company.grpc.employeecompany.
                Employee.newBuilder().setId(employee.getId()).setAddress(employee.getAddress())
                .setNic(employee.getNic()).setFullname(employee.getFullname())
                .setEmail(employee.getEmail()).setComId(employee.getComId()).build();
        return employeeCompanyClient.sendCompany(employee1);
    }
}
