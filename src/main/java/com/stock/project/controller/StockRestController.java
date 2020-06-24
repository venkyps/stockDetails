package com.stock.project.controller;

import com.stock.project.model.StockVO;
import com.stock.project.service.StockDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockRestController {

    private static Logger logger = LoggerFactory.getLogger(StockRestController.class);

    @Autowired
    private StockDetailsService stockDetailsService;

    @GetMapping("api/getStockPriceDetails")
    public ResponseEntity getStockPriceDetails() throws Exception{
        logger.info("inside StockRestController getStockPriceDetails");
        List<StockVO> stockVOList=null;
        try{
            stockVOList=stockDetailsService.getStockDetails();
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(stockVOList, HttpStatus.OK);
    }
}
