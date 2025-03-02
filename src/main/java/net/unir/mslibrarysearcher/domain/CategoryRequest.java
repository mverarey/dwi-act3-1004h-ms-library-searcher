package net.unir.mslibrarysearcher.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    private Long categoryId;
    @NotEmpty
    private String categoryName;
}
