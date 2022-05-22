package com.uniteam.unishop.service.serviceImpl;// Created by Sardorbek Matniyazov on 5/21/2022 at 11:27 AM

import com.uniteam.unishop.domain.Output;
import com.uniteam.unishop.domain.PaymentHistory;
import com.uniteam.unishop.domain.enums.PaymentStatus;
import com.uniteam.unishop.domain.enums.PaymentType;
import com.uniteam.unishop.payload.PayDto;
import com.uniteam.unishop.payload.Status;
import com.uniteam.unishop.repository.OutputRepo;
import com.uniteam.unishop.repository.PaymentHistoryRepo;
import com.uniteam.unishop.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@lombok.Data
@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {

    private final OutputRepo repo;
    private final PaymentHistoryRepo paymentHistoryRepo;

    @Override
    public List<Output> getAll() {
        return repo.findAllByStatus(PaymentStatus.DEBT);
    }

    @Override
    public Output get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Status pay(PayDto dto) {
        Optional<Output> byId = repo.findById(dto.getOutputId());
        if(byId.isPresent()){
            Output output = byId.get();
            if (dto.getPayCard() == 0.0 && dto.getPayCash() == 0.) return Status.PAYMENT_CASH_IS_NULL;
            Set<PaymentHistory> paymentHistories = output.getPaymentHistories();
            if (dto.getPayCard() != 0.) {
                output.setDebtAmount(
                        output.getDebtAmount() - dto.getPayCard()
                );
                paymentHistories.add(
                        new PaymentHistory(
                                dto.getPayCard(),
                                PaymentType.CARD,
                                output
                        )
                );
            }
            if (dto.getPayCash() != 0.) {
                output.setDebtAmount(
                        output.getDebtAmount() - dto.getPayCash()
                );
                paymentHistories.add(
                        new PaymentHistory(
                                dto.getPayCash(),
                                PaymentType.CASH,
                                output
                        )
                );
            }
            if (output.getDebtAmount() == 0.) output.setStatus(PaymentStatus.PAID);
            output = repo.save(output);
            return Status.setBodyToStatus(Status.SUCCESS_PAID, output);
        }

        return Status.PAYMENT_NOT_FOUNT;
    }


}
