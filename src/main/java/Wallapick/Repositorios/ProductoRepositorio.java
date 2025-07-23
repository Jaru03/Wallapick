package Wallapick.Repositorios;

import Wallapick.Modelos.Producto;
import Wallapick.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}
