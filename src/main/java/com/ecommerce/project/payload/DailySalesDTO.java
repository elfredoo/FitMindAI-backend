package com.ecommerce.project.payload;

import com.ecommerce.project.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DailySalesDTO {
    private Long id;
    private Long sellerId;
    private LocalDate date;
    private Integer soldCount = 0;
    private Double totalEarned = 0.0;
}
