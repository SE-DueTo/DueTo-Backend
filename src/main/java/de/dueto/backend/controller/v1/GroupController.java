package de.dueto.backend.controller.v1;

import de.dueto.backend.model.Transaction;
import de.dueto.backend.model.group.GroupAddNormalDTO;
import de.dueto.backend.model.group.GroupAndSumDTO;
import de.dueto.backend.model.user.User;
import de.dueto.backend.security.Session;
import de.dueto.backend.service.GroupService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("{groupId}")
    public GroupAndSumDTO getGroupInfo(HttpSession session, @PathVariable long groupId) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return groupService.getGroupInfo(user, groupId);
    }

    @GetMapping("{groupId}/transactions")
    public List<Transaction> getTransactions(
            HttpSession session,
            @PathVariable long groupId,
            @RequestParam long from,
            @RequestParam int limit) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return groupService.getTransactions(user, groupId, from, limit);
    }

    @GetMapping("{groupId}/debts")
    public List<Transaction> getDebts(
            HttpSession session,
            @PathVariable long groupId,
            @RequestParam long from,
            @RequestParam int limit) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return groupService.getTransactions(user, groupId, from, limit);
    }

    @GetMapping("normal/add")
    public long addNormalGroup(HttpSession session, @RequestBody GroupAddNormalDTO groupAddNormalDTO) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return groupService.addNormalGroup(user, groupAddNormalDTO);
    }

    @GetMapping("normal/remove/{groupId}")
    public boolean removeSpontaneousGroup(HttpSession session, @PathVariable long groupId) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return groupService.removeNormalGroup(groupId);
    }

    @GetMapping("spontaneous/add")
    public long addSpontaneousGroup(HttpSession session, @RequestBody long userId) {
        User user = (User) session.getAttribute(Session.USER_TOKEN);
        return groupService.addSpontaneousGroup(user, userId);
    }
}
