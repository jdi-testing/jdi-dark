package com.epam.jdi.dto;

import java.util.List;

public class Product {
    public Integer id;
    public String name;
    public Float price;
    public List<String> tags;
    public Dimensions dimensions;
    public WarehouseLocation warehouseLocation;

    private static class Dimensions {
        public Float length;
        public Float width;
        public Float height;
    }

    private static class WarehouseLocation {
        public Float latitude;
        public Float longitude;
    }
}
