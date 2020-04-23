package com.cs.platform.app.entity;

import com.cs.platform.framework.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pub_product")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;

    @Column(length = 200)
    private String name;

    /**
     * 封面
     */
    @Column(length = 200)
    private String cover;
    /**
     * 商品状态  0 上架 1下架
     */
    @Column(columnDefinition = "int(1) NOT NULL default '0'")
    private int state;

    /**
     * 库存
     */
    @Column(columnDefinition = "int(6) NOT NULL default '0'")
    private int stock;

    /**
     * 价格
     */
    @Column(columnDefinition = "float(8,2)")
    private float price;

    @Column(length = 200)
    private String imgSrc1;
    @Column(length = 200)
    private String imgSrc2;
    @Column(length = 200)
    private String imgSrc3;
    /**
     * 内容（文本）
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String contentHtml;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    @Where(clause = "del_flag='0'")
    @JsonIgnore
    private List<ClassifyProduct> list;

    @Transient
    private String[] classifyProducts;

    @Transient
    private String[] classifyProductNames;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ClassifyProduct> getList() {
        return list;
    }

    public void setList(List<ClassifyProduct> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc1() {
        return imgSrc1;
    }

    public void setImgSrc1(String imgSrc1) {
        this.imgSrc1 = imgSrc1;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String[] getClassifyProducts() {
        return classifyProducts;
    }

    public void setClassifyProducts(String[] classifyProducts) {
        this.classifyProducts = classifyProducts;
    }

    public String[] getClassifyProductNames() {
        return classifyProductNames;
    }

    public void setClassifyProductNames(String[] classifyProductNames) {
        this.classifyProductNames = classifyProductNames;
    }

    public String getImgSrc2() {
        return imgSrc2;
    }

    public void setImgSrc2(String imgSrc2) {
        this.imgSrc2 = imgSrc2;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getImgSrc3() {
        return imgSrc3;
    }

    public void setImgSrc3(String imgSrc3) {
        this.imgSrc3 = imgSrc3;
    }

    public int getState() {
        return state;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
