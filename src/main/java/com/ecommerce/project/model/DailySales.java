package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "daily_sales", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"seller_id", "date"})
})
@Data
@NoArgsConstructor
@Getter
@Setter
public class DailySales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_sales_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "sold_count", nullable = false)
    private Integer soldCount = 0;

    @Column(name = "total_earned", nullable = false)
    private Double totalEarned = 0.0;

    public DailySales(User seller, LocalDate date) {
        this.seller = seller;
        this.date = date;
    }
}
