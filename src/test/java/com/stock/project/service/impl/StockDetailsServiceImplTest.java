package com.stock.project.service.impl;

import com.stock.project.entity.StockDetails;
import com.stock.project.model.StockVO;
import com.stock.project.repo.StockDetailsRepository;
import com.stock.project.service.Impl.StockDetailsServiceImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class StockDetailsServiceImplTest {

    @Mock
    private StockDetailsRepository stockDetailsRepository;

    @InjectMocks
    private StockDetailsServiceImpl stockDetailsServiceImpl;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testGetStockDetails() throws Exception{
        List<StockDetails> stockDetailsList = new ArrayList<>();
        StockDetails stockDetails = new StockDetails();
        stockDetails.setAskPrice(23.9);
        stockDetails.setBidPrice(23.5);
        stockDetails.setEventDate(Timestamp.valueOf(LocalDateTime.now()));
        stockDetailsList.add(stockDetails);

        when(stockDetailsRepository.findAll()).thenReturn(stockDetailsList);

        List<StockVO> stockVOList= stockDetailsServiceImpl.getStockDetails();
        Assert.assertEquals(1,stockVOList.size());
    }

    @Test
    public void testGetStockDetailsEmpty() throws Exception{
        when(stockDetailsRepository.findAll()).thenReturn(new ArrayList<>());
        List<StockVO> stockVOList= stockDetailsServiceImpl.getStockDetails();
        Assert.assertEquals(0,stockVOList.size());
    }

    @Test
    public void testGetStockDetailsException() throws Exception{
        exceptionRule.expect(Exception.class);
        when(stockDetailsRepository.findAll()).thenThrow(Exception.class);
        List<StockVO> stockVOList= stockDetailsServiceImpl.getStockDetails();
    }
}
