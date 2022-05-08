package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.responses.FraudCheckResponse;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check/")
@RequiredArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId) {
        boolean isFraudster = fraudCheckService.isFraudster(customerId);
        log.info("Fraud check result: " + isFraudster + " for customer id: " + customerId);
        return FraudCheckResponse.builder().isFraudster(isFraudster).build();
    }

}
