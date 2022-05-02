/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ara.cardealership.dto;

import java.util.Objects;

/**
 *
 * @author Rich
 */
public class CarDto {
    
    private int carId;
    private String vinNum;
    private int makeId;
    private int modelId;
    private String type;
    private String bodyStyle;
    private int year;
    private String transmission;
    private String exteriorColor;
    private String interiorColor;
    private int mileage;
    private float msrp;
    private float salePrice;
    private String description;
    private boolean Featured;
    private boolean Sold;

    public boolean isFeatured() {
        return Featured;
    }

    public void setFeatured(boolean Featured) {
        this.Featured = Featured;
    }

    public boolean isSold() {
        return Sold;
    }

    public void setSold(boolean Sold) {
        this.Sold = Sold;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getVinNum() {
        return vinNum;
    }

    public void setVinNum(String vinNum) {
        this.vinNum = vinNum;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public float getMsrp() {
        return msrp;
    }

    public void setMsrp(float msrp) {
        this.msrp = msrp;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.carId;
        hash = 41 * hash + Objects.hashCode(this.vinNum);
        hash = 41 * hash + this.makeId;
        hash = 41 * hash + this.modelId;
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.bodyStyle);
        hash = 41 * hash + this.year;
        hash = 41 * hash + Objects.hashCode(this.transmission);
        hash = 41 * hash + Objects.hashCode(this.exteriorColor);
        hash = 41 * hash + Objects.hashCode(this.interiorColor);
        hash = 41 * hash + this.mileage;
        hash = 41 * hash + Float.floatToIntBits(this.msrp);
        hash = 41 * hash + Float.floatToIntBits(this.salePrice);
        hash = 41 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarDto other = (CarDto) obj;
        if (this.carId != other.carId) {
            return false;
        }
        if (this.makeId != other.makeId) {
            return false;
        }
        if (this.modelId != other.modelId) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if (Float.floatToIntBits(this.msrp) != Float.floatToIntBits(other.msrp)) {
            return false;
        }
        if (Float.floatToIntBits(this.salePrice) != Float.floatToIntBits(other.salePrice)) {
            return false;
        }
        if (!Objects.equals(this.vinNum, other.vinNum)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
            return false;
        }
        if (!Objects.equals(this.transmission, other.transmission)) {
            return false;
        }
        if (!Objects.equals(this.exteriorColor, other.exteriorColor)) {
            return false;
        }
        if (!Objects.equals(this.interiorColor, other.interiorColor)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }
    
    
    
    
}
