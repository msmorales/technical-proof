package com.mssv.tecpr.Entity;


import jakarta.validation.constraints.*;

// Record que representa la entidad Cliente
// Incluye validaciones para los campos obligatorios
public record Cliente(
    @NotNull(message = "El ID es obligatorio") String id,
    @NotBlank(message = "El nombre no puede estar vacío") String nombre,
    @Email(message = "El email debe ser válido") String email,
    @Min(value = 18, message = "La edad mínima es 18 años") int edad,
    @NotNull(message = "El tipo de cliente es obligatorio") TIpoCliente tipoCliente) {}
