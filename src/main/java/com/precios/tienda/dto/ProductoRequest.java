package com.precios.tienda.dto;

public record ProductoRequest(
        String codigoBarras,
        String nombre,
        String categoria,
        Double precioIndividual,
        Double precioCaja,
        Double precioSixpack,
        Double precioDocena,
        Double precioPaquete
) {
    // Validación inline del constructor compacto
    public ProductoRequest {
        if (codigoBarras == null || codigoBarras.isBlank()) {
            throw new IllegalArgumentException("El código de barras es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
    }
}
