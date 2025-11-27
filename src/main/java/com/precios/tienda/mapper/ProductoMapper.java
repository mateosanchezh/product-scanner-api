package com.precios.tienda.mapper;


import com.precios.tienda.dto.ProductoDTO;
import com.precios.tienda.dto.ProductoRequest;
import com.precios.tienda.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getCodigoBarras(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getPrecioIndividual(),
                producto.getPrecioCaja(),
                producto.getPrecioSixpack(),
                producto.getPrecioDocena(),
                producto.getPrecioPaquete(),
                producto.getFechaRegistro(),
                producto.getFechaActualizacion()
        );
    }

    public Producto toEntity(ProductoRequest request) {
        return Producto.builder()
                .codigoBarras(request.codigoBarras())
                .nombre(request.nombre())
                .categoria(request.categoria())
                .precioIndividual(request.precioIndividual())
                .precioCaja(request.precioCaja())
                .precioSixpack(request.precioSixpack())
                .precioDocena(request.precioDocena())
                .precioPaquete(request.precioPaquete())
                .build();
    }

    public void updateEntity(Producto producto, ProductoRequest request) {
        producto.setNombre(request.nombre());
        producto.setCategoria(request.categoria());
        producto.setPrecioIndividual(request.precioIndividual());
        producto.setPrecioCaja(request.precioCaja());
        producto.setPrecioSixpack(request.precioSixpack());
        producto.setPrecioDocena(request.precioDocena());
        producto.setPrecioPaquete(request.precioPaquete());
    }
}