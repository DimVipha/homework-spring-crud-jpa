package co.istad.springwebmvc.repository;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Boolean existsByName(String name);
}
