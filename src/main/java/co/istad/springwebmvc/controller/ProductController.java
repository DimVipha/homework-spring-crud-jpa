package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid,
                           @RequestBody ProductEditRequest request) {
        productService.editProductByUuid(uuid, request);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/uuid/{uuid}")
    void deleteByUuid(@PathVariable String uuid ){
        productService.deleteByUuid(uuid);
    }
    @DeleteMapping("{id}")
    void deletedById(@PathVariable Integer id){
        productService.deletedById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@RequestBody @Valid ProductCreateRequest request) {
        System.out.println("REQUEST: " + request);
        productService.createNewProduct(request);
    }

    @GetMapping
    ResponseEntity<?> findProducts(@RequestParam(required = false, defaultValue = "") String name,
                                @RequestParam(required = false, defaultValue = "true") Boolean status) {
        /*return Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, status)
        );*/
        return  new ResponseEntity<>(Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, status)
        ),HttpStatus.ACCEPTED);
        /*Map<String,Object> data=(Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, status)
        ));

        return  ResponseEntity.accepted().body(data);*/
    }

    @GetMapping("/{id}")
    Map<String, Object> findProductById(@PathVariable Integer id) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductById(id)
        );
    }

    @GetMapping("/uuid/{uuid}")
    Map<String, Object> findProductByUuid(@PathVariable String uuid) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductByUuid(uuid)
        );
    }

    @PutMapping("/db/")
    void  updateProductById(@Valid @RequestParam Integer id,
                            @RequestBody ProductEditRequest request){
        productService.updateProductById(id, request);
    }
    @GetMapping("/db/")
    List<ProductResponse> findAllProducts(){
        return productService.findAllProducts();
    }

    @PostMapping("/db/")
    void createAllProducts(@Valid @RequestBody ProductCreateRequest request){
        productService.createAllProducts(request);
    }
    @DeleteMapping("/db/")
    void dropProductById(@Valid @RequestParam Integer id){
        productService.deleteProductDbById(id);
    }

    @GetMapping("/db/id/")
    ProductResponse getProductById(@Valid @RequestParam Integer id){
        return productService.getProductById(id);
    }
    @GetMapping("/db/uuid/")
    ProductResponse getProductById(@Valid @RequestParam String uuid){
        return productService.getProductByUuid(uuid);
    }
}
