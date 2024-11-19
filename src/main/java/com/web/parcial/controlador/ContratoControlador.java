package com.web.parcial.controlador;

import com.web.parcial.DTO.ContratoDTO;
import com.web.parcial.modelo.Contrato;
import com.web.parcial.servicio.ContratoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/contratos")
public class ContratoControlador {

    @Autowired
    private ContratoServicio contratoServicio;

    @GetMapping
    public List<ContratoDTO> obtenerTodosLosContratos() {
        return contratoServicio.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> obtenerPorId(@PathVariable Long id) {
        ContratoDTO contrato = contratoServicio.getPorId(id);
        if (contrato != null) {
            return ResponseEntity.ok(contrato);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ContratoDTO> crearContrato(@RequestBody Contrato contrato) {
        ContratoDTO nuevoContrato = contratoServicio.crearContrato(contrato);
        return ResponseEntity.ok(nuevoContrato);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> actualizarContrato(@PathVariable Long id, @RequestBody ContratoDTO contrato) {
        ContratoDTO contratoActualizado = contratoServicio.actualizarContrato(id, contrato);
        if (contratoActualizado != null) {
            return ResponseEntity.ok(contratoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long id) {
        if (contratoServicio.eliminarContrato(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
