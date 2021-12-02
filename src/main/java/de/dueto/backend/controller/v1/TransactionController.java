package de.dueto.backend.controller.v1;

import de.dueto.backend.service.TransactionService;
import org.springframework.stereotype.Controller;

@Controller("/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
