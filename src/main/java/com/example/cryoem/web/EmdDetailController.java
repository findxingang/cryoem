package com.example.cryoem.web;

import com.example.cryoem.domain.EmdDetail;
import com.example.cryoem.exception.Result;
import com.example.cryoem.service.EmdDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangxingang
 */
@RestController
@RequestMapping("/api/emd/detail")
@RequiredArgsConstructor
public class EmdDetailController {
    private final EmdDetailService emdDetailService;

    @GetMapping
    public Result<EmdDetail> getEmdDetail(@RequestParam(name = "fileName") String fileName) {
        EmdDetail emdDetail = emdDetailService.getEmdDetailByFileName(fileName);
        return Result.ok(emdDetail);
    }

    @GetMapping("/list")
    public Result<List<EmdDetail>> listEmdDetail() {
        List<EmdDetail> list = emdDetailService.listEmdDetail();
        return Result.ok(list);
    }

    @PostMapping
    public Result<Boolean> saveEmdDetail(@RequestBody EmdDetail emdDetail) {
        boolean isSaved = emdDetailService.saveEmdDetail(emdDetail);
        return isSaved ? Result.ok() : Result.error("保存失败");
    }

    @PutMapping
    public Result<Boolean> updateEmdDetail(@RequestBody EmdDetail emdDetail) {
        boolean isUpdated = emdDetailService.updateById(emdDetail);
        return isUpdated ? Result.ok() : Result.error("更新失败");
    }
}
