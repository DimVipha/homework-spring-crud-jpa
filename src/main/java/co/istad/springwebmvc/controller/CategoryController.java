package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Category;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.repository.ProductRepository;
import co.istad.springwebmvc.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private  final ProductRepository productRepository;
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the categories",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categories not found",
                    content = @Content) })


    @GetMapping
    List<CategoryResponse> findCategories(){
        return categoryService.findCategories();
    }
    @GetMapping("/{id}")
    CategoryResponse findCategoryById(@PathVariable Integer id){
       return categoryService.findCategoryById(id);
    }

    @PostMapping
    void createNewCategory(@Valid @RequestBody CategoryRequest request){
        categoryService.creatNewCategory(request);
    }
/*    @GetMapping
    Map<String, Object> findCategories() {
        Map<String, Object> data = new HashMap<>();

        data.put("message", "Categories have been found successfully");
        data.put("data", List.of("Education", "Entertainment"));
        return data;
    }*/
    @PutMapping("/id")
    CategoryResponse editCategoryById(@Valid @PathVariable Integer id, @RequestBody CategoryRequest request){
        return  categoryService.editCategoryById(id,request);
    }
    @DeleteMapping("/{id}")
    void deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategoryById(id);
    }

}

