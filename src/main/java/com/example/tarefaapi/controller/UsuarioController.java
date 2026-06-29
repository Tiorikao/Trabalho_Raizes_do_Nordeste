package com.example.tarefaapi.controller;

import com.example.tarefaapi.model.Usuario;
import com.example.tarefaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("O nome do usuário é obrigatório.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("O email do usuário é obrigatório.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            return ResponseEntity.badRequest().body("A senha do usuário é obrigatória.");
        }

        if (usuario.getRole() == null || usuario.getRole().isBlank()) {
            usuario.setRole("CLIENTE");
        }

        usuario.setRole(usuario.getRole().toUpperCase());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioExistente.get();

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());

        if (usuarioAtualizado.getRole() == null || usuarioAtualizado.getRole().isBlank()) {
            usuario.setRole("CLIENTE");
        } else {
            usuario.setRole(usuarioAtualizado.getRole().toUpperCase());
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuarioSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario.get());
    }
}
