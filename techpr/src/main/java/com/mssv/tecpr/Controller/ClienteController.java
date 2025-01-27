package com.mssv.tecpr.Controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mssv.tecpr.Entity.Cliente;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Controlador para la gestión de clientes
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>(); // Lista para almacenar clientes en memoria

    // Endpoint para crear un nuevo cliente
    @PostMapping
    public ResponseEntity<String> crearCliente(@Valid @RequestBody Cliente cliente) {
        clientes.add(cliente); // Agregar el cliente a la lista
        return ResponseEntity.ok("Cliente creado exitosamente.");
    }

    // Endpoint para obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        return ResponseEntity.ok(clientes); // Retornar la lista de clientes
    }

    // Endpoint para actualizar los datos de un cliente por su ID
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable String id, @Valid @RequestBody Cliente clienteActualizado) {
        Optional<Cliente> clienteExistente = clientes.stream()
            .filter(c -> c.id().equals(id)) // Buscar cliente por ID
            .findFirst();
        if (clienteExistente.isPresent()) {
            clientes.remove(clienteExistente.get()); // Eliminar el cliente existente
            clientes.add(clienteActualizado); // Agregar el cliente actualizado
            return ResponseEntity.ok("Cliente actualizado exitosamente.");
        }
        return ResponseEntity.status(404).body("Cliente no encontrado.");
    }

    // Endpoint para eliminar un cliente por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable String id) {
        boolean eliminado = clientes.removeIf(c -> c.id().equals(id)); // Eliminar cliente por ID
        if (eliminado) {
            return ResponseEntity.ok("Cliente eliminado exitosamente.");
        }
        return ResponseEntity.status(404).body("Cliente no encontrado.");
    }

    // Endpoint para procesar descuentos según el tipo de cliente
    @GetMapping("/procesar/{id}")
    public ResponseEntity<String> procesarDescuento(@PathVariable String id) {
        Optional<Cliente> clienteExistente = clientes.stream()
            .filter(c -> c.id().equals(id)) // Buscar cliente por ID
            .findFirst();
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            // Determinar acción según el tipo de cliente
            String mensaje = switch (cliente.tipoCliente()) {
                case VIP -> "Descuento aplicado: 10%";
                case REGULAR -> "Sin descuento.";
            };
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.status(404).body("Cliente no encontrado.");
    }
}