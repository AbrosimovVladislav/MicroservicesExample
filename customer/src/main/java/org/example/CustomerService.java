package org.example;

import lombok.RequiredArgsConstructor;
import org.example.clients.FraudClient;
import org.example.clients.responses.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudResponse = fraudClient.isFraudster(customer.getId());
        if (fraudResponse.isFraudster()) {
            throw new IllegalStateException("Customer fraud with id " + customer.getId());
        }
        //check if fraudster
        //send notification
    }
}
