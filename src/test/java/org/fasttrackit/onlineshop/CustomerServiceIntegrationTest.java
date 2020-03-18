package org.fasttrackit.onlineshop;


import org.fasttrackit.onlineshop.domain.Customer;
import org.fasttrackit.onlineshop.service.CustomerService;
import org.fasttrackit.onlineshop.transfer.customer.SaveCustomerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void createCustomer(){

        SaveCustomerRequest request = new SaveCustomerRequest();
        request.setFirstName("FirstName");
        request.setLastName("LastName");

        Customer customer = customerService.createCustomer(request);
        assertThat(Customer)

    }

}
