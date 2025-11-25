package com.example.umc9th.global.validator;

import com.example.umc9th.domain.food.repository.FoodRepository;
import com.example.umc9th.global.annotation.ExistFoods;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode; // 기존 에러코드 사용
import jakarta.validation.ConstraintValidator; // Validation API
import jakarta.validation.ConstraintValidatorContext; // Validation API Context
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

// 1. @Component와 @RequiredArgsConstructor로 Spring Bean 및 Repository 주입 준비
@Component
@RequiredArgsConstructor
// 2. ConstraintValidator<어노테이션, 검증할 대상 타입> 인터페이스 구현
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> {

    private final FoodRepository foodRepository;

    // 3. 검증 로직: 리스트의 모든 값이 DB에 존재하는지 확인
    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {

        // 리스트의 모든 값(value)에 대해 foodRepository.existsById(value)를 실행 (DB 체크)
        boolean isValid = values.stream()
                .allMatch(value -> foodRepository.existsById(value));

        if (!isValid) {
            // 검증 실패 시, 기본 에러 메시지를 끄고 커스텀 메시지를 설정
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(GeneralErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid; // 모든 ID가 존재하면 true 반환
    }

    // (Optional) initialize 메서드는 기본 구현이 필요 없으므로 생략합니다.
}