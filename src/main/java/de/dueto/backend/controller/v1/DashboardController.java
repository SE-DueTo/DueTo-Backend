package de.dueto.backend.controller.v1;

import de.dueto.backend.model.settle_debt.SettleDebt;
import de.dueto.backend.model.dashboard.DashboardDataDTO;
import de.dueto.backend.model.transaction.TransactionDTO;
import de.dueto.backend.model.transaction.TransactionMapper;
import de.dueto.backend.model.user.User;
import de.dueto.backend.service.DashboardService;
import de.dueto.backend.service.SettleDebtService;
import de.dueto.backend.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final TransactionService transactionService;
    private final SettleDebtService settleDebtService;
    private final TransactionMapper transactionMapper;
    private final ControllerUtils controllerUtils;


    public DashboardController(
            DashboardService dashboardService,
            TransactionService transactionService,
            SettleDebtService settleDebtService,
            TransactionMapper transactionMapper,
            ControllerUtils controllerUtils) {
        this.dashboardService = dashboardService;
        this.transactionService = transactionService;
        this.settleDebtService = settleDebtService;
        this.transactionMapper = transactionMapper;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping("/")
    public DashboardDataDTO getData(@RequestHeader(value="Authorization") String token) {
        User user = controllerUtils.checkUser(token);
        return dashboardService.getData(user);
    }

    @GetMapping("/transactions")
    public List<TransactionDTO> transactions(
            @RequestHeader(value="Authorization") String token,
            @RequestParam long from,
            @RequestParam long limit) {
        User user = controllerUtils.checkUser(token);
        return transactionService.getTransactions(user, from, limit)
                .stream()
                .map(transactionMapper::fromTransaction)
                .toList();
    }

    @GetMapping("/debts")
    public List<SettleDebt> debts(
            @RequestHeader(value="Authorization") String token,
            @RequestParam long from,
            @RequestParam long limit) {
        User user = controllerUtils.checkUser(token);
        return settleDebtService.getDebts(user, from, limit);
    }
}
