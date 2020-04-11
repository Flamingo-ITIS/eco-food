package ru.itis.flamingo.ecofood.mapper;

import org.mapstruct.Mapper;
import ru.itis.flamingo.ecofood.domain.dto.RecallResponse;
import ru.itis.flamingo.ecofood.domain.entity.Recall;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface RecallResponseMapper extends Function<Recall, RecallResponse> {
}
