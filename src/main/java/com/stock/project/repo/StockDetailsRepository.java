package com.stock.project.repo;

import com.stock.project.entity.StockDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDetailsRepository extends CrudRepository<StockDetails, Long> {
}
