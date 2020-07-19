package ru.dfarkhutdinov.back.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dfarkhutdinov.back.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsById(Long id);

    boolean existsByName(String categoryName);

    Optional<Category> findByName(String name);

    Optional<Category> findByReqName(String name);

    List<Category> getAllByDeletedIsFalse();

    Optional<List<Category>> findByNameContaining(String name);
}
