package com.example.Hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private int medicineID;

    private String name;
    private String unit;
    private String description;
    private double price;

    @OneToMany(mappedBy = "medicine")
    private List<InvoiceMedicine> invoiceMedicines;

    @OneToMany(mappedBy = "medicine")
    private List<MedicineImport> medicineImports;
}
