package de.dueto.backend.controller.v1;

import de.dueto.backend.model.group.Group;
import de.dueto.backend.model.transaction.TransactionAddDTO;
import de.dueto.backend.model.user.User;
import de.dueto.backend.service.TransactionService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final AuthorizationMapper authorizationMapper;

    public TransactionController(TransactionService transactionService, AuthorizationMapper authorizationMapper) {
        this.transactionService = transactionService;
        this.authorizationMapper = authorizationMapper;
    }

    @PostMapping("add")
    public boolean addTransaction(
            @RequestHeader(value="Authorization") String token,
            @RequestBody TransactionAddDTO transactionAddDTO) {
        User user = authorizationMapper.getUser(token);
        if(user.getGroups()
                .stream()
                .mapToLong(Group::getGroupId)
                .noneMatch(groupId -> groupId == transactionAddDTO.getGroupId())) {
            return false;
        }
        return transactionService.addTransaction(transactionAddDTO);
    }

}
