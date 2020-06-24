package com.stock.project.controller;

import com.stock.project.service.StockDetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class StockRestControllerTest {

    @InjectMocks
    private StockRestController stockRestController;

    @Mock
    private StockDetailsService stockDetailsService;

    @Test
    public void testGetStockPriceDetails() throws Exception{
        stockRestController.getStockPriceDetails();
        Mockito.verify(stockDetailsService,Mockito.times(1)).getStockDetails();
        Assert.assertEquals(0, stockDetailsService.getStockDetails().size());
    }

    @Test
    public void testGetStockPriceDetailsRespone() throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Object> responseEntity = stockRestController.getStockPriceDetails();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetStockPriceDetailsException() throws Exception {
        when(stockDetailsService.getStockDetails()).thenThrow(Exception.class);
        ResponseEntity responseEntity = stockRestController.getStockPriceDetails();
        Assert.assertEquals("500 INTERNAL_SERVER_ERROR" ,responseEntity.getStatusCode().toString());

    }


}
