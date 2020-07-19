package ru.dfarkhutdinov.back.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.dfarkhutdinov.back.db.BannerRepository;
import ru.dfarkhutdinov.back.db.CategoryRepository;
import ru.dfarkhutdinov.back.entity.Banner;
import ru.dfarkhutdinov.back.exception.CommonException;
import ru.dfarkhutdinov.back.exception.ConflictException;
import ru.dfarkhutdinov.back.exception.NoContentException;
import ru.dfarkhutdinov.back.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BannerService {
    private final BannerRepository bannerRepository;
    private final CategoryRepository categoryRepository;

    public List<Banner> getAllBanners() {
        return bannerRepository.getAllByDeletedIsFalse();
    }

    public Banner addBanner(Banner banner) throws CommonException {
        if (banner.getName() == null || banner.getName().isEmpty() ||
                bannerRepository.existsByName(banner.getName())) {
            throw new ConflictException("Banner with this name already exists");
        }
        if (banner.getCategory() == null) {
            throw new ConflictException("Category cannot be null");
        }
        return bannerRepository.saveAndFlush(banner);
    }

    public Banner updateBanner(Banner banner) throws CommonException {
        bannerRepository.findById(banner.getId()).orElseThrow(NotFoundException::new);
        val possibleConflictBanner = bannerRepository.findByName(banner.getName());
        if (possibleConflictBanner.isPresent() && possibleConflictBanner.get() != banner) {
            throw new ConflictException("Update error: banner with this name already exists");
        }
        return bannerRepository.save(banner);
    }

    public Banner deleteBannerById(Long id) throws CommonException {
        val banner = bannerRepository.findById(id).orElseThrow(NotFoundException::new);
        banner.setDeleted(true);
        bannerRepository.save(banner);
        return banner;
    }

    public Banner findByName(String name) throws CommonException {
        val banners = bannerRepository.findByNameContaining(name).orElseThrow(NotFoundException::new);
        return getBannerWithHighestPrice(banners);
    }

    public String findBannerContentByCategoryReqName(String reqName) throws CommonException {
        val category = categoryRepository.findByReqName(reqName).orElseThrow(NotFoundException::new);
        val banners = bannerRepository.findByCategory(category).orElseThrow(NoContentException::new);
        return getBannerWithHighestPrice(banners).getContent();
    }

    private Banner getBannerWithHighestPrice(List<Banner> banners) {
        return banners.stream().sorted((b1, b2) -> b2.getPrice().compareTo(b1.getPrice())).
                collect(Collectors.toList()).get(0);
    }
}
