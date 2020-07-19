package ru.dfarkhutdinov.back.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.dfarkhutdinov.back.db.BannerRepository;
import ru.dfarkhutdinov.back.db.CategoryRepository;
import ru.dfarkhutdinov.back.entity.Category;
import ru.dfarkhutdinov.back.exception.CommonException;
import ru.dfarkhutdinov.back.exception.ConflictException;
import ru.dfarkhutdinov.back.exception.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BannerRepository bannerRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.getAllByDeletedIsFalse();
    }

    public Category addCategory(Category category) throws CommonException {
        if (category.getName() == null || category.getName().isEmpty() ||
                categoryRepository.existsByName(category.getName())) {
            throw new ConflictException("Category with this name already exists");
        }
        return categoryRepository.saveAndFlush(category);
    }

    public Category updateCategory(Category category) throws CommonException {
        categoryRepository.findById(category.getId()).orElseThrow(NotFoundException::new);
        val possibleConflictCategory = categoryRepository.findByName(category.getName());
        if (possibleConflictCategory.isPresent() && possibleConflictCategory.get() != category) {
            throw new ConflictException("Update error: category with this name already exists");
        }
        return categoryRepository.save(category);
    }

    public Category deleteCategoryById(Long id) throws CommonException {
        val category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
        val numOfLinkedBanners = bannerRepository.getNumOfBannersByCategoryId(category.getId());
        if (numOfLinkedBanners == 0) {
            category.setDeleted(true);
            categoryRepository.save(category);
        } else {
            throw new ConflictException("Cannot delete category because of active banners");
        }
        return category;
    }

    public Category findCategoryByName(String name) throws CommonException {
        val categories = categoryRepository.findByNameContaining(name).orElseThrow(NotFoundException::new);
        return categories.get(0);
    }
}
