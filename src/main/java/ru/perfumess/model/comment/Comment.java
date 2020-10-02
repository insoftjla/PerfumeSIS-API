package ru.perfumess.model.comment;

import lombok.*;
import ru.perfumess.model.BaseEntity;
import ru.perfumess.model.Customer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "comments")
public class Comment extends BaseEntity<String> {

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    private String header;

    private String text;
}
