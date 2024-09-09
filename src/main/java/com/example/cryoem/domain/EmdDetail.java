package com.example.cryoem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @TableName emd_detail
 */
@TableName(value ="emd_detail")
@Data
public class EmdDetail implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 写死为 Single-particle
     */
    @TableField(value = "field_name")
    private String fieldName;

    /**
     * 分辨率
     */
    @TableField(value = "resolution_ratio")
    private BigDecimal resolutionRatio;

    /**
     * 文件名称
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 提交日期
     */
    @TableField(value = "deposition")
    private String deposition;

    /**
     * 
     */
    @TableField(value = "map_released")
    private String mapReleased;

    /**
     * 
     */
    @TableField(value = "last_modified")
    private String lastModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private MultipartFile file;
}