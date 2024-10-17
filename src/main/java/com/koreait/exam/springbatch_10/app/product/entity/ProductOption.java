package com.koreait.exam.springbatch_10.app.product.entity;

import com.koreait.exam.springbatch_10.app.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductOption extends BaseEntity {
    private String color;
    private String size;
    private Integer price;

    private boolean isSoldout; // 관련 옵션들의 판매불가 여부
    private int stockQuantity; // 보유 물건 갯수

    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    private Product product;

    public ProductOption(String color, String size) {
        this.color = color;
        this.size = size;
    }

    public boolean isOrderable(int quantity) {
        if (isSoldout() == false) return true;

        return getStockQuantity() >= quantity;
    }
}