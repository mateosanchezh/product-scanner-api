package com.precios.tienda.service;
import com.precios.tienda.dto.ProductoDTO;
import com.precios.tienda.dto.ProductoRequest;
import com.precios.tienda.model.Producto;
import com.precios.tienda.mapper.ProductoMapper;
import com.precios.tienda.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public ProductoDTO buscarPorCodigo(String codigoBarras) {
        return repository.findByCodigoBarras(codigoBarras)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public ProductoDTO registrarProducto(ProductoRequest request) {
        if (repository.existsByCodigoBarras(request.codigoBarras())) {
            throw new IllegalArgumentException(
                    "El producto con código %s ya existe".formatted(request.codigoBarras())
            );
        }

        var producto = mapper.toEntity(request);
        var guardado = repository.save(producto);
        return mapper.toDTO(guardado);
    }

    @Transactional
    public ProductoDTO actualizarProducto(String codigoBarras, ProductoRequest request) {
        var producto = repository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado: " + codigoBarras
                ));

        mapper.updateEntity(producto, request);
        var actualizado = repository.save(producto);
        return mapper.toDTO(actualizado);
    }

    public List<ProductoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<ProductoDTO> buscarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria).stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<ProductoDTO> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional
    public void eliminarProducto(String codigoBarras) {
        var producto = repository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado: " + codigoBarras
                ));
        repository.delete(producto);
    }
}
