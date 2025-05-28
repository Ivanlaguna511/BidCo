package com.bidco.api_rest.dto;

import java.util.ArrayList;
import java.util.Arrays;

public class FiltroDTO {
    private int minPrice;
    private int maxPrice;
    private String[] categorias;
    private String dateOrder;

    public FiltroDTO() {}

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Override
    public String toString() {
        return "Filtro: minPrice = " + minPrice + ", maxPrice = " + maxPrice + ", categorias = " + Arrays.toString(categorias) + ", dateOrder = " + dateOrder;
    }
}