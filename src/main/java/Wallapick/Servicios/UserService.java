package Wallapick.Servicios;

import Wallapick.Modelos.Usuario;
import Wallapick.Repositorios.UsuarioRepositorio;
import Wallapick.Utils.JWTUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsuarioRepositorio userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsuarioRepositorio userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public String Login(Usuario user){
        Usuario existUser = userRepo.findByUsername(user.getUsername());

        if(existUser != null && passwordEncoder.matches(user.getPassword(),existUser.getPassword())){
            return
        }else{
            return "NO";
        }

    }
}
