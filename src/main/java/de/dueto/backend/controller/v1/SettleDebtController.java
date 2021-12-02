package de.dueto.backend.controller.v1;

import de.dueto.backend.service.SettleDebtService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/debt")
public class SettleDebtController {

    private final SettleDebtService settleDebtService;

    public SettleDebtController(SettleDebtService settleDebtService) {
        this.settleDebtService = settleDebtService;
    }
}
