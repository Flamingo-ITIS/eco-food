package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.RecallRequest;
import ru.itis.flamingo.ecofood.domain.dto.RecallResponse;

import java.util.List;

public interface RecallService {

    void saveRecall(String username, RecallRequest recallRequest);

    List<RecallResponse> findAllByProduct(Long productId);

    void deleteRecall(String username, Long recallId);

}
