package com.example.jpa_native_delete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Item {
    @Id
    @EqualsAndHashCode.Include
    private int id;

    @EqualsAndHashCode.Include
    private String name;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Cart cart;
}
