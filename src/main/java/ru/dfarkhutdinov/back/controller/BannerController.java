package ru.dfarkhutdinov.back.controller;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.dfarkhutdinov.back.dto.ResponseDTO;
import ru.dfarkhutdinov.back.entity.Banner;
import ru.dfarkhutdinov.back.exception.BadRequestException;
import ru.dfarkhutdinov.back.exception.CommonException;
import ru.dfarkhutdinov.back.service.BannerService;

import java.util.List;

@AllArgsConstructor
@RestController
public class BannerController {
    private final BannerService bannerService;

    @GetMapping("banners/all")
    public ResponseDTO<List<Banner>> getBanners() {
        return ResponseDTO.createResponse(bannerService.getAllBanners());
    }

    @PostMapping("banners/add")
    public ResponseDTO<Banner> addBanner(@RequestBody Banner banner) {
        val created = bannerService.addBanner(banner);
        return ResponseDTO.createResponse(created);
    }

    @PostMapping("banners/update")
    public ResponseDTO<Banner> updateBanner(@RequestBody Banner banner) {
        val updated = bannerService.updateBanner(banner);
        return ResponseDTO.createResponse(updated);
    }

    @DeleteMapping("banners/delete")
    public ResponseDTO<Banner> deleteBanner(@RequestParam String id) throws CommonException {
        try {
            val convertedId = Long.parseLong(id);
            Banner deleted = bannerService.deleteBannerById(convertedId);
            return ResponseDTO.createResponse(deleted);
        } catch (NumberFormatException ex) {
            throw new BadRequestException("id must be a number");
        }
    }

    @GetMapping("banners")
    public ResponseDTO<Banner> getBannerByName(@RequestParam String name) {
        val banner = bannerService.findByName(name);
        return ResponseDTO.createResponse(banner);
    }

    @GetMapping("bid")
    public ResponseDTO<String> getBannerByCategoryReqName(@RequestParam String category) {
        val bannerContent = bannerService.findBannerContentByCategoryReqName(category);
        return ResponseDTO.createResponse(bannerContent);
    }
}
