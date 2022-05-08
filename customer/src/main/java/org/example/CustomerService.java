package org.example;

import lombok.RequiredArgsConstructor;
import org.example.clients.FraudClient;
import org.example.clients.NotificationClient;
import org.example.clients.dto.FraudCheckResponse;
import org.example.clients.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

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
        notificationClient.notification(
                NotificationRequest.builder()
                        .toCustomerId(customer.getId().intValue())
                        .toCustomerEmail(customer.getEmail())
                        .message("New customer {} registration " + customer)
                        .build());
        //check if fraudster
        //send notification
    }
}
