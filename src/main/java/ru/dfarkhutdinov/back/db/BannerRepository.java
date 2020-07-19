package ru.dfarkhutdinov.back.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dfarkhutdinov.back.entity.Banner;
import ru.dfarkhutdinov.back.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
    boolean existsByName(String name);

    Optional<Banner> findByName(String name);

    List<Banner> getAllByDeletedIsFalse();

    @Query(value = "SELECT COUNT(*) FROM banners WHERE category_id = ?1 AND deleted = false")
    int getNumOfBannersByCategoryId(Long id);

    Optional<List<Banner>> findByNameContaining(String name);

    Optional<List<Banner>> findByCategory(Category category);
}
