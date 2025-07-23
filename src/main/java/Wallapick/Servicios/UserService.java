package Wallapick.Servicios;

import Wallapick.Modelos.Usuario;
import Wallapick.Repositorios.UsuarioRepositorio;
import Wallapick.Utils.JWTUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UsuarioRepositorio userRepo;
    private final PasswordEncoder passwordEncoder;
    private JWTUser jwtUser;

    public UserService(UsuarioRepositorio userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public String Register(Usuario user){
        Usuario existUser = userRepo.findByUsername(user.getUsername());

        if(existUser == null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return "REGISTRADO";
        }else{
            return "EXISTE";
        }
    }
    public String Login(Usuario user){
        Usuario existUser = userRepo.findByUsername(user.getUsername());

        if(existUser != null && passwordEncoder.matches(user.getPassword(),existUser.getPassword())){
            existUser.setRole("LOGGED");
            return jwtUser.GenerateToken(existUser);
        }else{
            return "ACCESO DENEGADO";
        }

    }

    public Usuario getUsers(long id,String token){
        try {
            jwtUser.ObtenerUsuario(token);
            Usuario u=  userRepo.findById(id).get();
            u.setPassword("");
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateUser(Usuario user,String token){
        try {
            Usuario userLogged = jwtUser.ObtenerUsuario(token);
            Usuario existingUser = userRepo.findById(user.getId()).get();
            if(userLogged.getRole().equals("LOGGED") || existingUser.getId().equals(userLogged.getId())) {

                userRepo.save(userLogged);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUser(long id,String token){
        try {
            Usuario userLogged = jwtUser.ObtenerUsuario(token);
            Usuario existingUser = userRepo.findById(id).get();
            if(userLogged.getRole().equals("LOGGED") || existingUser.getId().equals(userLogged.getId())) {
                userRepo.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Long obtenerIdSiLoginValido(String username, String password,String token) {
        try {
            Usuario userLogged = jwtUser.ObtenerUsuario(token);
            Usuario user = userRepo.findByUsername(username);
            if ((userLogged.getRole().equals("LOGGED")
                    && passwordEncoder.matches(password, user.getPassword()) &&
                    user.getUsername().equals(username))) {

                user.setPassword("");
                return user.getId();
            } else {
                return null;
            }
        }catch (Exception e) {
            return null;
        }
    }
}
