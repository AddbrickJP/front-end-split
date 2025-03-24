package com.ruoyi.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品信息表 sys_product
 * 
 * @author ruoyi
 */
public class SysProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    @Excel(name = "产品序号")
    private Long productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 主分类 */
    @Excel(name = "主分类")
    private String mainCategory;

    /** 子分类 */
    @Excel(name = "子分类")
    private String subCategory;

    /** 产品图片 */
    @Excel(name = "产品图片")
    private String image;

    /** 产品链接 */
    @Excel(name = "产品链接")
    private String link;

    /** 评分 */
    @Excel(name = "评分")
    private Double ratings;

    /** 评价数量 */
    @Excel(name = "评价数量")
    private Integer noOfRatings;

    /** 折扣价格 */
    @Excel(name = "折扣价格")
    private Double discountPrice;

    /** 实际价格 */
    @Excel(name = "实际价格")
    private Double actualPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @NotBlank(message = "产品名称不能为空")
    @Size(min = 0, max = 200, message = "产品名称不能超过200个字符")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public Integer getNoOfRatings() {
        return noOfRatings;
    }

    public void setNoOfRatings(Integer noOfRatings) {
        this.noOfRatings = noOfRatings;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("productId", getProductId())
                .append("name", getName())
                .append("mainCategory", getMainCategory())
                .append("subCategory", getSubCategory())
                .append("image", getImage())
                .append("link", getLink())
                .append("ratings", getRatings())
                .append("noOfRatings", getNoOfRatings())
                .append("discountPrice", getDiscountPrice())
                .append("actualPrice", getActualPrice())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}