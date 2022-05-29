package de.dueto.backend.service;

import de.dueto.backend.model.settle_debt.SettleDebt;
import de.dueto.backend.model.settle_debt.SettleDebtAddDTO;
import de.dueto.backend.model.settle_debt.SettleDebtMapper;
import de.dueto.backend.model.user.User;
import de.dueto.backend.mysql_data.SettleDebtRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettleDebtService {

    private final SettleDebtRepository settleDebtRepository;
    private final SettleDebtMapper settleDebtMapper;

    public SettleDebtService(SettleDebtRepository settleDebtRepository, SettleDebtMapper settleDebtMapper) {
        this.settleDebtRepository = settleDebtRepository;
        this.settleDebtMapper = settleDebtMapper;
    }

    public long getBalance(@NonNull User user) {
        return settleDebtRepository.getAllByDebtorEquals(user)
                .stream()
                .mapToLong(SettleDebt::getAmount)
                .sum();
    }

    @NonNull
    public List<SettleDebt> getDebts(@NonNull User user, long from, long limit) {
        return settleDebtRepository.getAllByDebtorEquals(user)
                .stream()
                .skip(from)
                .limit(limit)
                .toList();
    }

    @NonNull
    public List<SettleDebt> getDebts(@NonNull User user, long groupId, long from, long limit) {
        return getDebts(user, groupId)
                .stream()
                .skip(from)
                .limit(limit)
                .toList();
    }

    @NonNull
    public List<SettleDebt> getDebts(@NonNull User user, long groupId) {
        return settleDebtRepository.getAllByDebtorEqualsAndGroupEquals(user, groupId);
    }

    public boolean addSettleDebt(@NonNull SettleDebtAddDTO settleDebtAddDTO) {
        SettleDebt settleDebt = settleDebtMapper.fromSettleDebtAddDTO(settleDebtAddDTO);
        if(settleDebt==null) return false;
        settleDebtRepository.save(settleDebt);
        return true;
    }
}
