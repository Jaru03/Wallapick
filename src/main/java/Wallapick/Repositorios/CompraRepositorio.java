package Wallapick.Repositorios;

import Wallapick.Modelos.Compra;
import Wallapick.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepositorio extends JpaRepository<Compra,Long> {
}
