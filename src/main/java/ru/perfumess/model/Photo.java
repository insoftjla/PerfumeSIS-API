package ru.perfumess.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "url", callSuper = false)
@Table(name = "photos")
public class Photo extends BaseEntity<String> {

    private String url;
}
