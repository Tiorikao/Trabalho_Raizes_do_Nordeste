package com.example.tarefaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.tarefaapi.model.Pedido;
import com.example.tarefaapi.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    // LISTAR TODOS
    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }

    // SALVAR
    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {
        return repository.save(pedido);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {

        Pedido pedidoExistente = repository.findById(id).orElse(null);

        if (pedidoExistente == null) {
            return null;
        }

        pedidoExistente.setCliente(pedido.getCliente());
        pedidoExistente.setProduto(pedido.getProduto());
        pedidoExistente.setQuantidade(pedido.getQuantidade());
        pedidoExistente.setValorTotal(pedido.getValorTotal());

        return repository.save(pedidoExistente);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }

}