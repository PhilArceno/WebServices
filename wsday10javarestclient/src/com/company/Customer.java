package com.company;

public class Customer {

    public Customer(long id, String email, String name, long active) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.active = active;
    }

    public long id;
    public String email, name;
    public long active; // better: boolean isActive; or enum { Active, Inactive, Unknown }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
