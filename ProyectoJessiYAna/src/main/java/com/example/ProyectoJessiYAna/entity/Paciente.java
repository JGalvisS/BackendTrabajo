package com.example.ProyectoJessiYAna.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column//(nullable = false) // para que sea notnull
    private String cedula;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL)// ALL para que si borra un paciente tambien borre ese domicilio
    @JoinColumn(name = "domicilio_id",referencedColumnName = "id")// el uso de la primery key de domicilio que se reflejara en paciente como domicilio_id
    private Domicilio domicilio;
    @Column(unique = true)// para que sea unico
    private String email;
    @OneToMany(mappedBy = "paciente",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE) // se mapea por paciente y el fetch de tipo lazy para que cuando cargue no traiga los turnos obligatoriamente
    @JsonIgnore
    private Set<Turno> turnos=new HashSet<>(); // creamos un HashSet que va a guardar con Set haciendo que sea irrepetible en la lista

    public Paciente() {}

    public Paciente(String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email){
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente(Long id, String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente(Long id, String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email, Set<Turno> turnos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
        this.turnos = turnos;
    }
}