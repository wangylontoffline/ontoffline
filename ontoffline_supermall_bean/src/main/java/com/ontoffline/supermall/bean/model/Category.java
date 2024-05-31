/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
@TableName("oto_category")
public class Category implements Serializable {
	
    /**
     * 类目ID
     *
     */
    @TableId

    private Long categoryId;

    /**
     * 店铺id
     */
    private Long shopId;
    
    /**
     * 父节点
     */
    private Long parentId = 0L;

    /**
     * 产品类目名称
     */
    private String categoryName;

    /**
     * 类目图标
     */
    private String icon;

    /**
     * 类目的显示图片
     */
    private String pic;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 默认是1，表示正常状态,0为下线状态
     */
    private Integer status;

    /**
     * 记录时间
     */
    private Date recTime;

    /**
     * 分类层级
     */
    private Integer grade;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 品牌id
     */
    @TableField(exist=false)
    private List<Long> brandIds;
    
    /**
     * 参数id
     */
    @TableField(exist=false)
    private List<Long> attributeIds;

    /**
     * 品牌列表
     */
    @TableField(exist=false)
    private List<Brand> brands;
    
    /**
     * 参数列表
     */
    @TableField(exist=false)
    private List<ProdProp> prodProps;
    
    /**
     * 商品列表
     */
    @TableField(exist=false)
    private List<Product> products;
    
    @TableField(exist=false)
    private List<Category> categories;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getRecTime() {
		return recTime;
	}

	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Long> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Long> brandIds) {
		this.brandIds = brandIds;
	}

	public List<Long> getAttributeIds() {
		return attributeIds;
	}

	public void setAttributeIds(List<Long> attributeIds) {
		this.attributeIds = attributeIds;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<ProdProp> getProdProps() {
		return prodProps;
	}

	public void setProdProps(List<ProdProp> prodProps) {
		this.prodProps = prodProps;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
    
    
}