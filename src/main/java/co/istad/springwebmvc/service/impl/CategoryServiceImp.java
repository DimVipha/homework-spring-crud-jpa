package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Category;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.repository.CategoryRepository;
import co.istad.springwebmvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {

    private  final CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> findCategories() {
        List<Category>  categories= categoryRepository.findAll();

        return categories.stream().map(category -> new CategoryResponse(category.getName(),category.getDescription())).toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "category has not found"
        ));
        return new CategoryResponse(category.getName(),category.getDescription());
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        return null;
    }

    @Override
    public void creatNewCategory(CategoryRequest request) {
        if(categoryRepository.existsByName(request.name()
        )){
            new ResponseStatusException(HttpStatus.CONFLICT,"category name is already existed!!!");
        }

        Category category=new Category();
        category.setName(request.name());
        category.setDescription(request.description());
//        categoryRepository.save(category);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse editCategoryById(Integer id, CategoryRequest request) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Category has not been found"
        ));
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
        return findCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found");
        });
        categoryRepository.deleteById(id);
    }
}
