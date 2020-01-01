package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    //按照买家微信Openid查询  分页
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
