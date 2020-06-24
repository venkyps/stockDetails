package com.stock.project.service;

import com.stock.project.model.StockVO;

import java.util.List;

public interface StockDetailsService {

    List<StockVO> getStockDetails() throws Exception;
}
