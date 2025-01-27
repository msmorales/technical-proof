package com.mssv.tecpr.Entity;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

// Clase que representa la entidad Prestamo
public class Prestamo {
    @NotNull(message = "El ID es obligatorio")
    private String id; // Identificador único del préstamo

    @Positive(message = "El monto debe ser positivo")
    private double monto; // Monto del préstamo

    @NotNull(message = "El ID del cliente es obligatorio")
    private String clienteId; // ID del cliente asociado al préstamo

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha; // Fecha del préstamo

    @NotNull(message = "El estado del préstamo es obligatorio")
    private EstadoPrestamo estado; // Estado del préstamo

    // Constructor de la clase Prestamo
    public Prestamo(String id, double monto, String clienteId, LocalDate fecha, EstadoPrestamo estado) {
        this.id = id;
        this.monto = monto;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Métodos getter y setter para cada atributo
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

}