package com.precios.tienda.controller;


import com.precios.tienda.dto.ProductoDTO;
import com.precios.tienda.dto.ProductoRequest;
import com.precios.tienda.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping("/{codigoBarras}")
    public ResponseEntity<ProductoDTO> buscarProducto(@PathVariable String codigoBarras) {
        var producto = service.buscarPorCodigo(codigoBarras);
        return producto != null
                ? ResponseEntity.ok(producto)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> registrarProducto(@RequestBody ProductoRequest request) {
        try {
            var producto = service.registrarProducto(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{codigoBarras}")
    public ResponseEntity<ProductoDTO> actualizarProducto(
            @PathVariable String codigoBarras,
            @RequestBody ProductoRequest request) {
        try {
            var producto = service.actualizarProducto(codigoBarras, request);
            return ResponseEntity.ok(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigoBarras}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String codigoBarras) {
        try {
            service.eliminarProducto(codigoBarras);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String nombre) {

        List<ProductoDTO> productos;

        if (categoria != null) {
            productos = service.buscarPorCategoria(categoria);
        } else if (nombre != null) {
            productos = service.buscarPorNombre(nombre);
        } else {
            productos = service.listarTodos();
        }

        return ResponseEntity.ok(productos);
    }
}
