package co.istad.springwebmvc.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.experimental.NonFinal;

public record ProductCreateRequest(
        @NotBlank
        String name,
        @Positive
        Double price,
        @Positive
        @NotNull
        @Max(100)
        Integer qty
) {
}
