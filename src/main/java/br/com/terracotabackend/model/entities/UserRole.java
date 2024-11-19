package br.com.terracotabackend.model.entities;

public enum UserRole {

    ROLE_CUSTOMER("role_customer"),
    ROLE_CRAFTSMAN("role_craftsman"),
    ROLE_COMPANY("role_company"),
    ROLE_USER("role_user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
