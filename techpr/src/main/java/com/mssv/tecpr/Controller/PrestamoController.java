package com.mssv.tecpr.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mssv.tecpr.Entity.EstadoPrestamo;
import com.mssv.tecpr.Entity.Prestamo;
import com.mssv.tecpr.Entity.TIpoCliente;

import jakarta.validation.Valid;

// Controlador para la gestión de préstamos
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private List<Prestamo> prestamos = new ArrayList<>(); // Lista para almacenar préstamos en memoria

    // Endpoint para crear un nuevo préstamo
    @PostMapping
    public ResponseEntity<String> crearPrestamo(@Valid @RequestBody Prestamo prestamo) {
        prestamos.add(prestamo); // Agregar el préstamo a la lista
        return ResponseEntity.ok("Préstamo creado exitosamente.");
    }

    // Endpoint para obtener todos los préstamos activos (estado PENDIENTE)
    @GetMapping("/activos")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosActivos() {
        List<Prestamo> activos = prestamos.stream()
            .filter(p -> p.getEstado() == EstadoPrestamo.PENDIENTE) // Filtrar préstamos pendientes
            .toList();
        return ResponseEntity.ok(activos);
    }

    // Endpoint para actualizar el estado de un préstamo por su ID
    @PutMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstadoPrestamo(@PathVariable String id, @RequestParam EstadoPrestamo nuevoEstado) {
        Optional<Prestamo> prestamoExistente = prestamos.stream()
            .filter(p -> p.getId().equals(id)) // Buscar préstamo por ID
            .findFirst();
        if (prestamoExistente.isPresent()) {
            prestamoExistente.get().setEstado(nuevoEstado); // Actualizar estado del préstamo
            return ResponseEntity.ok("Estado del préstamo actualizado exitosamente.");
        }
        return ResponseEntity.status(404).body("Préstamo no encontrado.");
    }

    // Endpoint para eliminar un préstamo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPrestamo(@PathVariable String id) {
        boolean eliminado = prestamos.removeIf(p -> p.getId().equals(id)); // Eliminar préstamo por ID
        if (eliminado) {
            return ResponseEntity.ok("Préstamo eliminado exitosamente.");
        }
        return ResponseEntity.status(404).body("Préstamo no encontrado.");
    }

    // Endpoint para calcular el monto total a pagar según el tipo de cliente
    @GetMapping("/calcular/{clienteId}/{monto}")
    public ResponseEntity<String> calcularTotalPagar(@PathVariable String clienteId, @PathVariable double monto, @RequestParam TIpoCliente tipoCliente) {
        if (monto <= 0) {
            return ResponseEntity.badRequest().body("El monto debe ser mayor a cero.");
        }
        // Calcular la tasa de interés según el tipo de cliente
        double interes = (tipoCliente == TIpoCliente.VIP) ? 0.05 : 0.10;
        double total = monto + (monto * interes); // Calcular monto total con interés
        return ResponseEntity.ok("El monto total a pagar es: " + total);
    }
}
