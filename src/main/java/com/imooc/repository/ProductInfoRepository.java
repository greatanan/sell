package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    //根据商品状态查询
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
