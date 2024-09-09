package com.example.cryoem.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cryoem.domain.EmdDetail;
import com.example.cryoem.exception.BaseException;
import com.example.cryoem.service.EmdDetailService;
import com.example.cryoem.mapper.EmdDetailMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
* @author 王新刚
* @description 针对表【emd_detail】的数据库操作Service实现
* @createDate 2024-09-09 20:05:24
*/
@Service
public class EmdDetailServiceImpl extends ServiceImpl<EmdDetailMapper, EmdDetail>
    implements EmdDetailService{

    @Override
    public EmdDetail getEmdDetailByFileName(String fileName) {
        LambdaQueryWrapper<EmdDetail> queryWrapper = new LambdaQueryWrapper<EmdDetail>().eq(EmdDetail::getFileName, fileName);
        return getOne(queryWrapper);
    }

    @Override
    public List<EmdDetail> listEmdDetail() {
        return list();
    }

    @Override
    public boolean saveEmdDetail(EmdDetail emdDetail) {
        if (Objects.isNull(emdDetail.getId())) {
            emdDetail.setId(IdWorker.getId());
        }
        if (StrUtil.isEmpty(emdDetail.getName())) {
            throw new BaseException("文件名不能为空！");
        }
        if (StrUtil.isEmpty(emdDetail.getFieldName())) {
            emdDetail.setFieldName("Single-particle");
        }
        if (Objects.isNull(emdDetail.getResolutionRatio())) {
            emdDetail.setResolutionRatio(BigDecimal.ONE);
        }
        if (StrUtil.isEmpty(emdDetail.getFileName())) {
            String originalFilename = emdDetail.getFile().getOriginalFilename();
            emdDetail.setFileName(originalFilename);
        }
        if (StrUtil.isBlank(emdDetail.getDeposition())) {
            emdDetail.setDeposition(DateUtil.now());
        }
        if (StrUtil.isBlank(emdDetail.getMapReleased())) {
            emdDetail.setMapReleased(DateUtil.now());
        }
        if (StrUtil.isBlank(emdDetail.getLastModified())) {
            emdDetail.setLastModified(DateUtil.now());
        }
        return save(emdDetail);
    }
}




