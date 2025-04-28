package com.example.demo.dto;

import java.util.List;

public class ProductCollection {

private List<ProductDTO> data;

private AdditionalData additionalData;


public static class AdditionalData {

private String storeName;

private String storeProductsLink;


public AdditionalData(String storeName, String storeProductsLink) {

this.storeName = storeName;

this.storeProductsLink = storeProductsLink;

}


// Getters y setters

public String getStoreName() {

return storeName;

}

public void setStoreName(String storeName) {

this.storeName = storeName;

}

public String getStoreProductsLink() {

return storeProductsLink;

}

public void setStoreProductsLink(String storeProductsLink) {

this.storeProductsLink = storeProductsLink;

}

}


public ProductCollection(List<ProductDTO> data, AdditionalData additionalData) {

this.data = data;

this.additionalData = additionalData;

}


// Getters y setters

public List<ProductDTO> getData() {

return data;

}

public void setData(List<ProductDTO> data) {

this.data = data;

}

public AdditionalData getAdditionalData() {

return additionalData;

}

public void setAdditionalData(AdditionalData additionalData) {

this.additionalData = additionalData;

}

}
