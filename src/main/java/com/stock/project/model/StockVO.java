package com.stock.project.model;

public class StockVO {

    private String symbol;
    private Double price;
    private boolean trend;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isTrend() {
        return trend;
    }

    public void setTrend(boolean trend) {
        this.trend = trend;
    }

    @Override
    public String toString() {
        return "StockVO{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", trend=" + trend +
                '}';
    }
}
