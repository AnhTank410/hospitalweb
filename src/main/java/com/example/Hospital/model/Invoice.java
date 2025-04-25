package com.example.Hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "invoice")
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private int invoiceID;

    @Column(name = "total_amount")
    private double total;

    @Column(name = "payment_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceMedicine> invoiceMedicines;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceService> invoiceServices;

}
