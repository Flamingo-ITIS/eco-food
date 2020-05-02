package ru.itis.flamingo.ecofood.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.domain.dto.RecallRequest;
import ru.itis.flamingo.ecofood.domain.dto.RecallResponse;
import ru.itis.flamingo.ecofood.service.RecallService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recalls")
public class RecallController {

    private final RecallService recallService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity saveRecall(@AuthenticationPrincipal Principal principal,
                                     @RequestBody @Validated RecallRequest recallRequest) {
        recallService.saveRecall(principal.getName(), recallRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<List<RecallResponse>> getProductRecalls(@PathVariable Long productId) {
        return new ResponseEntity<>(recallService.findAllByProduct(productId), HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteRecall(@AuthenticationPrincipal Principal principal,
                                       @RequestParam Long recallId) {

        recallService.deleteRecall(principal.getName(), recallId);
        return ResponseEntity.ok().build();
    }

}
