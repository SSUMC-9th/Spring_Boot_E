package com.example.umc9th.global.validation.validator;

import com.example.umc9th.global.api.ErrorStatus;
import com.example.umc9th.global.validation.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < 1) {
            // 기본 메시지 비활성화
            context.disableDefaultConstraintViolation();
            // 커스텀 에러 메시지 설정 (ErrorStatus 활용)
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_VALID.toString())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}