package kenrick.demo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MyRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String title;

    private Long amount;

    private String owner;

    private LocalDate date;

    public MyRecord() {
    }

    public MyRecord(Integer id, String title, Long amount, String owner, LocalDate date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.owner = owner;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
