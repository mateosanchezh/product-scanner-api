package com.precios.tienda.repository;

import com.precios.tienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByCodigoBarras(String codigoBarras);
    boolean existsByCodigoBarras(String codigoBarras);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}