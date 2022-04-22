package de.dueto.backend.controller.v1;

import de.dueto.backend.model.settle_debt.SettleDebt;
import de.dueto.backend.model.dashboard.DashboardDataDTO;
import de.dueto.backend.model.transaction.TransactionDTO;
import de.dueto.backend.model.transaction.TransactionMapper;
import de.dueto.backend.service.DashboardService;
import de.dueto.backend.service.SettleDebtService;
import de.dueto.backend.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final TransactionService transactionService;
    private final SettleDebtService settleDebtService;
    private final AuthorizationMapper authorizationMapper;
    private final TransactionMapper transactionMapper;


    public DashboardController(DashboardService dashboardService, TransactionService transactionService, SettleDebtService settleDebtService, AuthorizationMapper authorizationMapper, TransactionMapper transactionMapper) {
        this.dashboardService = dashboardService;
        this.transactionService = transactionService;
        this.settleDebtService = settleDebtService;
        this.authorizationMapper = authorizationMapper;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("/")
    public DashboardDataDTO getData(@RequestHeader(value="Authorization") String token) {
        return dashboardService.getData(authorizationMapper.getUser(token));
    }

    @GetMapping("/transactions")
    public List<TransactionDTO> transactions(
            @RequestHeader(value="Authorization") String token,
            @RequestParam long from,
            @RequestParam long limit) {
        return transactionService.getTransactions(authorizationMapper.getUser(token), from, limit)
                .stream()
                .map(transactionMapper::fromTransaction)
                .collect(Collectors.toList());
    }

    @GetMapping("/debts")
    public List<SettleDebt> debts(
            @RequestHeader(value="Authorization") String token,
            @RequestParam long from,
            @RequestParam long limit) {
        return settleDebtService.getDebts(authorizationMapper.getUser(token), from, limit);
    }
}
