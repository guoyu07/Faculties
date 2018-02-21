package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObj;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@EqualsAndHashCode
public class Subject extends BaseObj {

    @Column(name = "subject", unique = true)
    private String subject;
}

