package com.example.BidCoPrueba.Controladores;

import com.example.BidCoPrueba.Modelos.Usuario;
import com.example.BidCoPrueba.Servicios.UserService;
import com.example.BidCoPrueba.util.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UserService usuarioService;

    @Autowired
    public UsuarioController(UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado"));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/crear")  // Método POST para crear un usuario
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (!usuarioService.obtenerPorId(id).isPresent()) {
            throw new UsuarioNoEncontradoException("No se puede actualizar. Usuario con ID " + id + " no encontrado");
        }
        usuario.setId(id);
        Usuario usuarioActualizado = usuarioService.guardar(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioService.obtenerPorId(id).isPresent()) {
            throw new UsuarioNoEncontradoException("No se puede eliminar. Usuario con ID " + id + " no encontrado");
        }
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Manejo de excepciones personalizadas
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<String> manejarUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Método GET para mostrar el formulario
    @GetMapping("/formulario")
    public String mostrarFormulario() {
        return "static/formulario.html";  // Redirige a la página estática formulario.html
    }
}
