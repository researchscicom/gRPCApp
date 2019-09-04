package com.company.grpc;

import com.company.grpc.employeecompany.Company;
import com.company.grpc.employeecompany.Employee;
import com.company.grpc.employeecompany.EmployeeCompanyServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class EmployeeCompanyServiceImpl extends EmployeeCompanyServiceGrpc.EmployeeCompanyServiceImplBase {

    @Override
    public void sendCompany(Employee employee, StreamObserver<Company> responseCompany) {
        long id = employee.getId();
        Company company = Company.newBuilder().setId(id).setName("Hasee").setCost(5000)
                .setQuantity(10).setDescription("Hellow "+id).build();
        System.out.println(company);
        responseCompany.onNext(company);
        responseCompany.onCompleted();
    }
}
