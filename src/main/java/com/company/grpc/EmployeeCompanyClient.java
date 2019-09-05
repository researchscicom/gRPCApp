package com.company.grpc;

import com.company.grpc.employeecompany.Company;
import com.company.grpc.employeecompany.Employee;
import com.company.grpc.employeecompany.EmployeeCompanyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EmployeeCompanyClient {
    private EmployeeCompanyServiceGrpc.EmployeeCompanyServiceBlockingStub employeeCompanyServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 6565).usePlaintext().build();

        employeeCompanyServiceBlockingStub = EmployeeCompanyServiceGrpc.newBlockingStub(managedChannel);
    }

    public Object sendCompany(Employee employee)
    {
        System.out.println(employee.toString());
        Company company = employeeCompanyServiceBlockingStub.sendCompany(employee);
        System.out.println(company);
        return company.toString();
    }
}
