package de.dueto.backend.controller.v1;

import de.dueto.backend.model.dashboard.DashboardDataDTO;
import de.dueto.backend.model.transaction.Transaction;
import de.dueto.backend.model.transaction.TransactionAddDTO;
import de.dueto.backend.model.user.User;
import de.dueto.backend.security.Session;
import de.dueto.backend.service.DashboardService;
import de.dueto.backend.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("add")
    public boolean addTransaction(@RequestBody TransactionAddDTO transactionAddDTO) {
        return transactionService.addTransaction(transactionAddDTO);
    }

}
