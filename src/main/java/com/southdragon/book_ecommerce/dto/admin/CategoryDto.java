package com.southdragon.book_ecommerce.dto.admin;

import com.southdragon.book_ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private String slug;
    private CategoryDto parent;

    public static CategoryDto fromEntity(Category category) {
        CategoryDto parentDto = null;
        if (category.getParent() != null) {
            parentDto = CategoryDto.builder()
                    .id(category.getParent().getId())
                    .name(category.getParent().getName())
                    .slug(category.getParent().getSlug())
                    .build();
        }

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .parent(parentDto)
                .build();
    }
}
