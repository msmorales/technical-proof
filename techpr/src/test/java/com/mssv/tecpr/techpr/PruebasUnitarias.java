package com.mssv.tecpr.techpr;


import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.mssv.tecpr.Controller.ClienteController;
import com.mssv.tecpr.Controller.PrestamoController;
import com.mssv.tecpr.Entity.Cliente;
import com.mssv.tecpr.Entity.EstadoPrestamo;
import com.mssv.tecpr.Entity.Prestamo;
import com.mssv.tecpr.Entity.TIpoCliente;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PruebasUnitarias {

    @Test
    public void testCrearCliente() {
        ClienteController controller = new ClienteController();
        Cliente cliente = new Cliente("1", "Juan Perez", "juan.perez@example.com", 25, TIpoCliente.REGULAR);

        ResponseEntity<String> response = controller.crearCliente(cliente);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cliente creado exitosamente.", response.getBody());
    }

    @Test
    public void testObtenerClientes() {
        ClienteController controller = new ClienteController();
        Cliente cliente = new Cliente("1", "Juan Perez", "juan.perez@example.com", 25, TIpoCliente.REGULAR);
        controller.crearCliente(cliente);

        ResponseEntity<List<Cliente>> response = controller.obtenerClientes();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testActualizarCliente() {
        ClienteController controller = new ClienteController();
        Cliente cliente = new Cliente("1", "Juan Perez", "juan.perez@example.com", 25, TIpoCliente.REGULAR);
        controller.crearCliente(cliente);

        Cliente clienteActualizado = new Cliente("1", "Juan Perez", "juan.nuevo@example.com", 25, TIpoCliente.VIP);
        ResponseEntity<String> response = controller.actualizarCliente("1", clienteActualizado);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cliente actualizado exitosamente.", response.getBody());
    }

    @Test
    public void testEliminarCliente() {
        ClienteController controller = new ClienteController();
        Cliente cliente = new Cliente("1", "Juan Perez", "juan.perez@example.com", 25, TIpoCliente.REGULAR);
        controller.crearCliente(cliente);

        ResponseEntity<String> response = controller.eliminarCliente("1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cliente eliminado exitosamente.", response.getBody());
    }

    @Test
    public void testCrearPrestamo() {
        PrestamoController controller = new PrestamoController();
        Prestamo prestamo = new Prestamo("1", 1000.0, "1", LocalDate.now(), EstadoPrestamo.PENDIENTE);

        ResponseEntity<String> response = controller.crearPrestamo(prestamo);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Préstamo creado exitosamente.", response.getBody());
    }

    @Test
    public void testObtenerPrestamosActivos() {
        PrestamoController controller = new PrestamoController();
        Prestamo prestamo = new Prestamo("1", 1000.0, "1", LocalDate.now(), EstadoPrestamo.PENDIENTE);
        controller.crearPrestamo(prestamo);

        ResponseEntity<List<Prestamo>> response = controller.obtenerPrestamosActivos();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testActualizarEstadoPrestamo() {
        PrestamoController controller = new PrestamoController();
        Prestamo prestamo = new Prestamo("1", 1000.0, "1", LocalDate.now(), EstadoPrestamo.PENDIENTE);
        controller.crearPrestamo(prestamo);

        ResponseEntity<String> response = controller.actualizarEstadoPrestamo("1", EstadoPrestamo.PAGADO);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Estado del préstamo actualizado exitosamente.", response.getBody());
    }

    @Test
    public void testEliminarPrestamo() {
        PrestamoController controller = new PrestamoController();
        Prestamo prestamo = new Prestamo("1", 1000.0, "1", LocalDate.now(), EstadoPrestamo.PENDIENTE);
        controller.crearPrestamo(prestamo);

        ResponseEntity<String> response = controller.eliminarPrestamo("1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Préstamo eliminado exitosamente.", response.getBody());
    }

    @Test
    public void testCalcularTotalPagar() {
        PrestamoController controller = new PrestamoController();
        ResponseEntity<String> response = controller.calcularTotalPagar("1", 1000.0, TIpoCliente.VIP);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("El monto total a pagar es: 1050.0"));
    }
}
