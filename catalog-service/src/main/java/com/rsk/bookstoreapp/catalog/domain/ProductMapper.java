package com.rsk.bookstoreapp.catalog.domain;

class ProductMapper {
    static Product toProduct(ProductEntity productEntity){
        return new Product(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice()
        );
    }
}
