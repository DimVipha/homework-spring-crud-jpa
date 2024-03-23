package co.istad.springwebmvc.repository;

import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer > {
    Boolean existsByName(String Name);
    Product findByUuid(String uuid);
}
