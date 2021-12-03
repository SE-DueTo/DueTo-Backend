package de.dueto.backend.controller.v1;

import de.dueto.backend.model.settleDebt.SettleDebt;
import de.dueto.backend.model.transaction.Transaction;
import de.dueto.backend.model.dashboard.DashboardDataDTO;
import de.dueto.backend.model.user.User;
import de.dueto.backend.security.Session;
import de.dueto.backend.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController("/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public DashboardDataDTO getData(HttpSession session) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return dashboardService.getData(user);
    }

    @GetMapping("/transactions")
    public List<Transaction> transactions(HttpSession session, @RequestBody long from, @RequestBody long limit) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return dashboardService.getTransactions(user, from, limit);
    }

    @GetMapping("/debts")
    public List<SettleDebt> debts(HttpSession session, @RequestBody long from, @RequestBody long limit) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return dashboardService.getDebts(user, from, limit);
    }
}
