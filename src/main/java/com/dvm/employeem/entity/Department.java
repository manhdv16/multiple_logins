package com.dvm.employeem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name ="established_date")
    private LocalDate establishedDate;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Employee> listEmployee;

}
