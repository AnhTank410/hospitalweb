package com.example.Hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="service_id")
    private int serviceID;

    private String name;
    private String description;
    private double price;

    @OneToMany(mappedBy = "service")
    private List<InvoiceService> invoiceServices;
}
