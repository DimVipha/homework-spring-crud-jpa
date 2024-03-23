package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Category;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.repository.ProductRepository;
import co.istad.springwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private List<Product> products;

    private  final ProductRepository  productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setUuid(UUID.randomUUID().toString());
        p1.setName("iPhone 14 Pro Max");
        p1.setPrice(1300.0);
        p1.setQty(1);
        p1.setImportedDate(LocalDate.now());
        p1.setStatus(true);
        Product p2 = new Product();
        p2.setId(2);
        p2.setUuid(UUID.randomUUID().toString());
        p2.setName("macBook M3 RAM 30GB");
        p2.setPrice(2600.0);
        p2.setQty(2);
        p2.setImportedDate(LocalDate.now());
        p2.setStatus(true);
        Product p3 = new Product();
        p3.setId(3);
        p3.setUuid(UUID.randomUUID().toString());
        p3.setName("macBook M3 Pro RAM 30GB");
        p3.setPrice(2500.0);
        p3.setQty(1);
        p3.setImportedDate(LocalDate.now());
        p3.setStatus(false);
        Product p4 = new Product();
        p4.setId(3);
        p4.setUuid(UUID.randomUUID().toString());
        p4.setName("macBook M3 RAM 18GB");
        p4.setPrice(2200.0);
        p4.setQty(1);
        p4.setImportedDate(LocalDate.now());
        p4.setStatus(true);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
    }

    @Override
    public void editProductByUuid(String uuid, ProductEditRequest request) {
        // Check UUID if exists
        long count = products.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .peek(oldProduct -> {
                    oldProduct.setName(request.name());
                    oldProduct.setPrice(request.price());
                }).count();
        System.out.println("Affected row = " + count);
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setId(products.size() + 1);
        newProduct.setUuid(UUID.randomUUID().toString());
        newProduct.setImportedDate(LocalDate.now());
        newProduct.setStatus(true);
        products.add(newProduct);
    }
    @Override
    public void deleteByUuid(String uuid) {
        products.removeIf(product -> product.getUuid().equals(uuid));
    }

    @Override
    public void deletedById(Integer id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public List<ProductResponse> findProducts(String name, Boolean status) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name.toLowerCase()) && product.getStatus().equals(status))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .toList();


    }

    @Override
    public ProductResponse findProductById(Integer id) {
        /*return products.stream()
                .filter(product -> product.getId().equals(id) &&
                        product.getStatus().equals(true))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .findFirst()
                .orElseThrow();*/
//        products =productRepository.findById(id);
        return null;
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        return products.stream()
                .filter(product -> product.getUuid().equals(uuid) &&
                        product.getStatus().equals(true))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        products=productRepository.findAll();
        return products.stream().map(
                product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                        )
        ).toList();
    }

    @Override
    public ProductResponse getProductByUuid(String uuid) {
        Product product=productRepository.findByUuid(uuid);
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(),product.getQty());
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Product product=productRepository.findById(id).orElseThrow(()->new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product has not been found"
        ));
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(),product.getQty());
    }


    @Override
    public void createAllProducts(ProductCreateRequest request) {
        if(productRepository.existsByName(request.name())){
            new ResponseStatusException(HttpStatus.CONFLICT,"product name is already existed!!!");
        }
        Product p=new Product();
        p.setName(request.name());
        p.setPrice(request.price());
        p.setQty(request.qty());
        p.setUuid(UUID.randomUUID().toString());
        p.setStatus(true);
        p.setImportedDate(LocalDate.now());
        productRepository.save(p);
    }

    @Override
    public void deleteProductDbById(Integer id) {
        if (productRepository.existsById(id)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Product has not been found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public void updateProductById(Integer id, ProductEditRequest request) {
        Product p=new Product();
        p=productRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("Product id not been found ")
        );
        p.setName(request.name());
        p.setPrice(request.price());
        productRepository.save(p);
    }


}
