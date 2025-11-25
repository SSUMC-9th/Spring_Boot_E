package com.umc_study.mission_server.common.validator;

import com.umc_study.mission_server.common.annotation.ExistsFoodType;
import com.umc_study.mission_server.store.exception.StoreErrorCode;
import com.umc_study.mission_server.store.repository.FoodTypeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodTypeExistenceValidator implements ConstraintValidator<ExistsFoodType, List<Long>> {
    private final FoodTypeRepository foodTypeRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
            .allMatch(foodTypeRepository::existsById);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(StoreErrorCode.FOOD_TYPE_NOT_FOUND.getMessage())
                .addConstraintViolation();
        }

        return isValid;
    }
}