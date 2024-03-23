package co.istad.springwebmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CategoryRequest(
        @NotBlank
        String name,
        String description
) {
}
