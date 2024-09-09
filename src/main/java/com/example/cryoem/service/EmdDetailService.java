package com.example.cryoem.service;

import com.example.cryoem.domain.EmdDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 王新刚
* @description 针对表【emd_detail】的数据库操作Service
* @createDate 2024-09-09 20:05:24
*/
public interface EmdDetailService extends IService<EmdDetail> {

    EmdDetail getEmdDetailByFileName(String fileName);

    List<EmdDetail> listEmdDetail();

    boolean saveEmdDetail(EmdDetail emdDetail);
}
