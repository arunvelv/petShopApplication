package com.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="transaction_id",  nullable=false)
    private int transactionId;

    @Column(name="transaction_date",  nullable=false)
    private Date transactionDate;

    @Column(name="amount",  nullable=false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name="transaction_status")
    private TransactionStatus transactionStatus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customers;

    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JsonIgnore
    @JoinColumn(name = "pet_id")
    private Pets pets;

    public enum TransactionStatus {
    	SUCCESS,
        FAILED
    }
    
    public Transactions() {}
    
    

	public Transactions(int transactionId, Date transactionDate, double amount, TransactionStatus transactionStatus,
			Customer customers, Pets pets) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.transactionStatus = transactionStatus;
		this.customers = customers;
		this.pets = pets;
	}



	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Customer getCustomer() {
		return customers;
	}

	public void setCustomer(Customer customers) {
		this.customers = customers;
	}

	public Pets getPet() {
		return pets;
	}

	public void setPet(Pets pets) {
		this.pets = pets;
	}
    
    
}