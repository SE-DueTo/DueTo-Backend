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
    private final ControllerUtils controllerUtils;

    public TransactionController(
            TransactionService transactionService,
            ControllerUtils controllerUtils) {
        this.transactionService = transactionService;
        this.controllerUtils = controllerUtils;
    }

    @PostMapping("add")
    public boolean addTransaction(
            @RequestHeader(value="Authorization") String token,
            @RequestBody TransactionAddDTO transactionAddDTO) {
        User user = controllerUtils.checkUser(token);
        if(user.getGroups()
                .stream()
                .mapToLong(Group::getGroupId)
                .noneMatch(groupId -> groupId == transactionAddDTO.getGroupId())) {
            return false;
        }
        return transactionService.addTransaction(transactionAddDTO);
    }

}
