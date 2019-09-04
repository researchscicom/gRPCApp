package com.company.grpc;

import com.company.grpc.employeecompany.Company;
import com.company.grpc.employeecompany.Employee;
import com.company.grpc.employeecompany.EmployeeCompanyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EmployeeCompanyClient {
    private EmployeeCompanyServiceGrpc.EmployeeCompanyServiceBlockingStub employeeCompanyServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8080).usePlaintext().build();

        employeeCompanyServiceBlockingStub = EmployeeCompanyServiceGrpc.newBlockingStub(managedChannel);
    }

    public byte sendCompany(Employee employee)
    {
        System.out.println(employee.toByteString());
        Company company = employeeCompanyServiceBlockingStub.sendCompany(employee);
        return company.toByteString();
    }
}
