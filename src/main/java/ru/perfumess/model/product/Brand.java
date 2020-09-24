package ru.perfumess.model.product;

import lombok.*;
import ru.perfumess.model.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = false)
@Table(name = "brands")
public class Brand extends BaseEntity {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String about;

}
