package br.com.agibank.typeprocessor.dto;

import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@SuppressWarnings("restriction")
@Setter
@XmlRootElement(name = "transactionRecord")
public class Transaction {
    private String username;
    private int userId;
    private LocalDateTime transactionDate;
    private double amount;
 
    /* getters and setters for the attributes */
 
    @Override
    public String toString() {
        return "Transaction [username=" + username + ", userId=" + userId
          + ", transactionDate=" + transactionDate + ", amount=" + amount
          + "]";
    }
}