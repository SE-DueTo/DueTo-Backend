package de.dueto.backend.controller.v1;

import de.dueto.backend.model.settleDebt.SettleDebtAddDTO;
import de.dueto.backend.service.SettleDebtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v1/debt")
public class SettleDebtController {

    private final SettleDebtService settleDebtService;

    public SettleDebtController(SettleDebtService settleDebtService) {
        this.settleDebtService = settleDebtService;
    }

    @PostMapping("/add")
    public boolean addSettleDebtAddDTO(@RequestBody SettleDebtAddDTO settleDebtAddDTO) {
        return settleDebtService.addSettleDebt(settleDebtAddDTO);
    }

}
