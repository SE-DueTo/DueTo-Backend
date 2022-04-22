package de.dueto.backend.controller.v1;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.group.GroupAddNormalDTO;
import de.dueto.backend.model.group.GroupAndSumDTO;
import de.dueto.backend.model.settle_debt.SettleDebtDTO;
import de.dueto.backend.model.settle_debt.SettleDebtMapper;
import de.dueto.backend.model.transaction.TransactionDTO;
import de.dueto.backend.model.transaction.TransactionMapper;
import de.dueto.backend.service.GroupService;
import de.dueto.backend.service.SettleDebtService;
import de.dueto.backend.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    private final GroupService groupService;
    private final TransactionService transactionService;
    private final SettleDebtService settleDebtService;
    private final AuthorizationMapper authorizationMapper;
    private final TransactionMapper transactionMapper;
    private final SettleDebtMapper settleDebtMapper;


    public GroupController(
            GroupService groupService,
            TransactionService transactionService,
            SettleDebtService settleDebtService,
            AuthorizationMapper authorizationMapper,
            TransactionMapper transactionMapper,
            SettleDebtMapper settleDebtMapper) {
        this.groupService = groupService;
        this.transactionService = transactionService;
        this.authorizationMapper = authorizationMapper;
        this.settleDebtService = settleDebtService;
        this.transactionMapper = transactionMapper;
        this.settleDebtMapper = settleDebtMapper;
    }

    @GetMapping("{groupId}")
    public GroupAndSumDTO getGroupInfo(
            @RequestHeader(value="Authorization") String token,
            @PathVariable long groupId) {
        return groupService.getGroupInfo(authorizationMapper.getUser(token), groupId);
    }

    @GetMapping("{groupId}/transactions")
    public List<TransactionDTO> getTransactions(
            @RequestHeader(value="Authorization") String token,
            @PathVariable long groupId,
            @RequestBody long from,
            @RequestBody int limit) {
        Group group = groupService.getGroupById(groupId);
        return transactionService.getTransactions(authorizationMapper.getUser(token), group, from, limit)
                .stream()
                .map(transactionMapper::fromTransaction)
                .collect(Collectors.toList());
    }

    @GetMapping("{groupId}/debts")
    public List<SettleDebtDTO> getDebts(
            @RequestHeader(value="Authorization") String token,
            @PathVariable long groupId,
            @RequestBody long from,
            @RequestBody int limit) {
        return settleDebtService.getDebts(authorizationMapper.getUser(token), groupId, from, limit)
                .stream()
                .map(settleDebtMapper::fromSettleDebt)
                .collect(Collectors.toList());
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
            @RequestBody long userId) {
        return groupService.addSpontaneousGroup(authorizationMapper.getUser(token), userId);
    }
}
