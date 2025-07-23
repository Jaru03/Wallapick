package Wallapick.Controladores;

import Wallapick.Modelos.Producto;
import Wallapick.Servicios.ProductoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControler {

    private ProductoServicio productoServicio;

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarProductos(@RequestParam String nombreParcial) {
        List<Producto> productos = productoServicio.buscarProductosPorNombreParcial(nombreParcial);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
}
