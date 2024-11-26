package br.com.terracotabackend.model.enums;

public enum UserRole {

    CUSTOMER("customer"),
    CRAFTSMAN("craftsman"),
    COMPANY("company"),
    USER("user"),
    ADMIN("admin");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return "ROLE_" + role.toUpperCase();
    }

}
