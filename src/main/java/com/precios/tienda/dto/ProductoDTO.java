package com.precios.tienda.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductoDTO(
        Long id,
        String codigoBarras,
        String nombre,
        String categoria,
        Double precioIndividual,
        Double precioCaja,
        Double precioSixpack,
        Double precioDocena,
        Double precioPaquete,
        LocalDateTime fechaRegistro,
        LocalDateTime fechaActualizacion
) {}
