package org.fasttrackit.onlineshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private  final CostumerRepository;
    private final ObjectMapper objectMapper;

    public CustomerService(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }


}
