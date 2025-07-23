package Wallapick.Servicios;

import Wallapick.Modelos.Producto;
import Wallapick.Repositorios.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {
    private final ProductoRepositorio productoRepositorio;

    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public List<Producto> buscarProductosPorNombreParcial(String nombreParcial) {
        return productoRepositorio.findByNombreContainingIgnoreCase(nombreParcial);
    }
}
