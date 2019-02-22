package com.example.toolRental.enums;

public enum Tool {
    LADW(ToolType.LADDER, "Werner"),
    CHNS(ToolType.CHAINSAW, "Stihl"),
    JAKR(ToolType.JACKHAMMER, "Ridgid"),
    JAKD(ToolType.JACKHAMMER, "DeWalt");

    private ToolType toolType;
    private String brand;

    Tool(ToolType toolType, String brand) {
        this.toolType = toolType;
        this.brand = brand;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }
}
