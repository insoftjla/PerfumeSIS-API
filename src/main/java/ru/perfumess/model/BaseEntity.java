package ru.perfumess.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<U> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(name = "created_by")
    private U createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "update_by")
    private U updateBy;

    @LastModifiedDate
    private Date updatedDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
