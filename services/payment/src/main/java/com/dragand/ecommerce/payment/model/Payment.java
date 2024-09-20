package com.dragand.ecommerce.payment.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue
    private Integer id;

    private BigDecimal amount;

    @Enumerated(STRING)
    private PaymentMethod paymentMethod;

    Integer orderId;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;




}
