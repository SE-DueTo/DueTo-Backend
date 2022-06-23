package de.dueto.backend.controller.v1;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.group.GroupAddNormalDTO;
import de.dueto.backend.model.group.GroupAndSumDTO;
import de.dueto.backend.model.settle_debt.SettleDebtDTO;
import de.dueto.backend.model.settle_debt.SettleDebtMapper;
import de.dueto.backend.model.transaction.TransactionDTO;
import de.dueto.backend.model.transaction.TransactionMapper;
import de.dueto.backend.model.user.User;
import de.dueto.backend.model.user.UserIdDTO;
import de.dueto.backend.service.GroupService;
import de.dueto.backend.service.SettleDebtService;
import de.dueto.backend.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    private final GroupService groupService;
    private final TransactionService transactionService;
    private final SettleDebtService settleDebtService;
    private final TransactionMapper transactionMapper;
    private final SettleDebtMapper settleDebtMapper;
    private final ControllerUtils controllerUtils;


    public GroupController(
            GroupService groupService,
            TransactionService transactionService,
            SettleDebtService settleDebtService,
            TransactionMapper transactionMapper,
            SettleDebtMapper settleDebtMapper,
            ControllerUtils controllerUtils) {
        this.groupService = groupService;
        this.transactionService = transactionService;
        this.settleDebtService = settleDebtService;
        this.transactionMapper = transactionMapper;
        this.settleDebtMapper = settleDebtMapper;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping("{groupId}")
    public GroupAndSumDTO getGroupInfo(
            @RequestHeader(value="Authorization") String token,
            @PathVariable Long groupId) {
        User user = controllerUtils.checkUser(token);
        return groupService.getGroupInfo(user, groupId);
    }

    @GetMapping("{groupId}/transactions")
    public List<TransactionDTO> getTransactions(
            @RequestHeader(value="Authorization") String token,
            @PathVariable long groupId,
            @RequestParam long from,
            @RequestParam int limit) {
        User user = controllerUtils.checkUser(token);
        Group group = controllerUtils.checkGroup(groupId);
        return transactionService.getTransactions(user, group, from, limit)
                .stream()
                .map(transactionMapper::fromTransaction)
                .toList();
    }

    @GetMapping("{groupId}/debts")
    public List<SettleDebtDTO> getDebts(
            @RequestHeader(value="Authorization") String token,
            @PathVariable long groupId,
            @RequestParam long from,
            @RequestParam int limit) {
        User user = controllerUtils.checkUser(token);
        Group group = controllerUtils.checkGroup(groupId);
        return settleDebtService.getDebts(user, group, from, limit)
                .stream()
                .map(settleDebtMapper::fromSettleDebt)
                .toList();
    }

    @PostMapping("normal/add")
    public long addNormalGroup(
            @RequestBody GroupAddNormalDTO groupAddNormalDTO) {
        return groupService.addNormalGroup(groupAddNormalDTO);
    }

    @PostMapping("normal/remove/{groupId}")
    public boolean removeSpontaneousGroup(@PathVariable long groupId) {
        return groupService.removeNormalGroup(groupId);
    }

    @PostMapping("spontaneous/add")
    public long addSpontaneousGroup(
            @RequestHeader(value="Authorization") String token,
            @RequestBody UserIdDTO userIdDTO) {
        User user = controllerUtils.checkUser(token);
        return groupService.addSpontaneousGroup(user, userIdDTO.getUserId());
    }
}
