package br.com.terracotabackend.model.enums;

public enum TypeProduct {

    DECORATION("decoration"),
    FASHION("fashion"),
    UTILITY("utility"),
    ARTS("arts"),
    CHILDREN("children"),
    COOKING("'cooking"),
    COSMETICS("cosmetics");

    private String value;

    TypeProduct(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
