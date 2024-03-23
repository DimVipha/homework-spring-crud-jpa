package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findCategories();
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse findCategoryByName(String name);
    void creatNewCategory(CategoryRequest request);
    CategoryResponse editCategoryById(Integer id, CategoryRequest request);
    void deleteCategoryById(Integer id);


}
