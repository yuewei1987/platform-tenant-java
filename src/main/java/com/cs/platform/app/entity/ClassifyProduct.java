package com.cs.platform.app.entity;

import com.cs.platform.framework.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "pub_classify_product")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ClassifyProduct extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;


    @ManyToOne
    @JoinColumn(name = "classify_id")
    private Classify classify;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Where(clause = "del_flag='0'")
    private Product product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
