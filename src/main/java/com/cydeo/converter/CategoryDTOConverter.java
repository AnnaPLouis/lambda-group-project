package com.cydeo.converter;

import com.cydeo.dto.CategoryDto;
import com.cydeo.service.CategoryService;
import org.springframework.core.convert.converter.Converter;

public class CategoryDTOConverter implements Converter<String, CategoryDto> {
    private final CategoryService categoryService;

    public CategoryDTOConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto convert(String source) {
        return categoryService.findById(Long.parseLong(source));
    }
}
