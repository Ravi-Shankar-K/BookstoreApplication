package com.rsk.bookstoreapp.catalog.domain;

import com.rsk.bookstoreapp.catalog.ApplicationProperties;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ApplicationProperties properties;

    ProductService(ProductRepository productRepository,
                   ApplicationProperties properties){
        this.properties = properties;
        this.productRepository = productRepository;
    }

    public PagedResult<Product> getProducts(int pageNo){
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);

        Page<Product> productsPage =
                productRepository.findAll(pageable)
                        .map(ProductMapper::toProduct);

        return new PagedResult<>(
                productsPage.getTotalElements(),
                productsPage.getNumber()+1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious(),
                productsPage.getContent()
        );
    }

    public Optional<Product> getProductByCode(String code){
        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
