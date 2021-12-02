package de.dueto.backend.controller.v1;

import de.dueto.backend.service.SettleDebtService;
import org.springframework.stereotype.Controller;

@Controller("/v1/debt")
public class SettleDebtController {

    private final SettleDebtService settleDebtService;

    public SettleDebtController(SettleDebtService settleDebtService) {
        this.settleDebtService = settleDebtService;
    }
}
