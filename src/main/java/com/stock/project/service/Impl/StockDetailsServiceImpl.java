package com.stock.project.service.Impl;

import com.stock.project.entity.StockDetails;
import com.stock.project.model.StockVO;
import com.stock.project.repo.StockDetailsRepository;
import com.stock.project.service.StockDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class StockDetailsServiceImpl implements StockDetailsService {

    private static Logger logger = LoggerFactory.getLogger(StockDetailsServiceImpl.class);

    @Autowired
    StockDetailsRepository stockDetailsRepository;

    public List<StockVO> getStockDetails() throws Exception{

        logger.info("inside StockDetailsServiceImpl getStockDetails");

        List<StockDetails> stockDetailsList = (List<StockDetails>)stockDetailsRepository.findAll();

        List<StockVO> stockVOList = new ArrayList<>();
        stockDetailsList.stream().filter(Objects::nonNull).forEach(stockDetail ->{
            StockVO stockVO = new StockVO();
            stockVO.setSymbol(stockDetail.getSymbol());
            Double price= (stockDetail.getBidPrice()+stockDetail.getAskPrice())/2;
            stockVO.setPrice(price);
            if(price<stockDetail.getBidPrice()){
                stockVO.setTrend(true);
            }
            stockVOList.add(stockVO);
        });

        logger.info("Stock details list "+stockVOList);
        return stockVOList;
    }
}
