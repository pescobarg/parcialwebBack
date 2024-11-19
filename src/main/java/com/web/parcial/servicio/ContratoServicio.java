package com.web.parcial.servicio;

import com.web.parcial.DTO.ContratoDTO;
import com.web.parcial.modelo.Contrato;
import com.web.parcial.repositorio.ContratoRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratoServicio {

    @Autowired
    private ContratoRepositorio contratoRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    public List<ContratoDTO> getAll() {
        return contratoRepositorio.findAll()
                .stream()
                .map(contrato -> modelMapper.map(contrato, ContratoDTO.class))
                .collect(Collectors.toList());
    }

    public ContratoDTO getPorId(Long id) {
        Optional<Contrato> contrato = contratoRepositorio.findById(id);
        return contrato.map(c -> modelMapper.map(c, ContratoDTO.class)).orElse(null);
    }

    public ContratoDTO crearContrato(Contrato contrato) {
        Contrato nuevoContrato = contratoRepositorio.save(contrato);
        return modelMapper.map(nuevoContrato, ContratoDTO.class);
    }

    public ContratoDTO actualizarContrato(Long id, ContratoDTO contratoDTO) {
        Contrato contratoExistente = contratoRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No hay un contrato con id = " + id));

        boolean haCambiado = false;

        if (!contratoExistente.getIdentificador().equals(contratoDTO.getIdentificador())) {
            contratoExistente.setIdentificador(contratoDTO.getIdentificador());
            haCambiado = true;
        }
        if (!contratoExistente.getValor().equals(contratoDTO.getValor())) {
            contratoExistente.setValor(contratoDTO.getValor());
            haCambiado = true;
        }
        if (!contratoExistente.getNombreContratante().equals(contratoDTO.getNombreContratante())) {
            contratoExistente.setNombreContratante(contratoDTO.getNombreContratante());
            haCambiado = true;
        }
        if (!contratoExistente.getDocumentoContratante().equals(contratoDTO.getDocumentoContratante())) {
            contratoExistente.setDocumentoContratante(contratoDTO.getDocumentoContratante());
            haCambiado = true;
        }
        if (!contratoExistente.getNombreContratantista().equals(contratoDTO.getNombreContratantista())) {
            contratoExistente.setNombreContratantista(contratoDTO.getNombreContratantista());
            haCambiado = true;
        }
        if (!contratoExistente.getDocumentoContratantista().equals(contratoDTO.getDocumentoContratantista())) {
            contratoExistente.setDocumentoContratantista(contratoDTO.getDocumentoContratantista());
            haCambiado = true;
        }
        if (!contratoExistente.getFechaInicial().equals(contratoDTO.getFechaInicial())) {
            contratoExistente.setFechaInicial(contratoDTO.getFechaInicial());
            haCambiado = true;
        }
        if (!contratoExistente.getFechaFinal().equals(contratoDTO.getFechaFinal())) {
            contratoExistente.setFechaFinal(contratoDTO.getFechaFinal());
            haCambiado = true;
        }

        if (haCambiado) {
            contratoRepositorio.save(contratoExistente);
        }

        return modelMapper.map(contratoExistente, ContratoDTO.class);
    }

    public boolean eliminarContrato(Long id) {
        if (contratoRepositorio.existsById(id)) {
            contratoRepositorio.deleteById(id);
            return true;
        }
        return false;
    }
}
