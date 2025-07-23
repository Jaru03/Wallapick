package Wallapick.Repositorios;

import Wallapick.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);

}
