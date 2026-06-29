package com.example.tarefaapi.controller;

import com.example.tarefaapi.model.Pedido;
import com.example.tarefaapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);

        if (pedidoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pedido pedido = pedidoExistente.get();

        pedido.setCliente(pedidoAtualizado.getCliente());
        pedido.setProduto(pedidoAtualizado.getProduto());
        pedido.setQuantidade(pedidoAtualizado.getQuantidade());
        pedido.setValorTotal(pedidoAtualizado.getValorTotal());
        pedido.setCanalPedido(pedidoAtualizado.getCanalPedido());
        pedido.setStatusPedido(pedidoAtualizado.getStatusPedido());
        pedido.setStatusPagamento(pedidoAtualizado.getStatusPagamento());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return ResponseEntity.ok(pedidoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pedidoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);

        if (pedidoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pedido pedido = pedidoExistente.get();

        String novoStatus = body.get("statusPedido");

        if (novoStatus == null || novoStatus.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        pedido.setStatusPedido(novoStatus);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return ResponseEntity.ok(pedidoSalvo);
    }

    @PostMapping("/{id}/pagamentos/mock")
    public ResponseEntity<?> pagamentoMock(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);

        if (pedidoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pedido pedido = pedidoExistente.get();

        String statusPagamento = body.get("statusPagamento");

        if (statusPagamento == null || statusPagamento.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "erro", "statusPagamento é obrigatório",
                    "exemplo", "APROVADO ou RECUSADO"
            ));
        }

        statusPagamento = statusPagamento.toUpperCase();

        if (statusPagamento.equals("APROVADO")) {
            pedido.setStatusPagamento("APROVADO");
            pedido.setStatusPedido("PAGO");

            Pedido pedidoSalvo = pedidoRepository.save(pedido);

            return ResponseEntity.ok(Map.of(
                    "pedidoId", pedidoSalvo.getId(),
                    "statusPagamento", pedidoSalvo.getStatusPagamento(),
                    "statusPedido", pedidoSalvo.getStatusPedido(),
                    "mensagem", "Pagamento mock aprovado com sucesso"
            ));
        }

        if (statusPagamento.equals("RECUSADO")) {
            pedido.setStatusPagamento("RECUSADO");
            pedido.setStatusPedido("PAGAMENTO_RECUSADO");

            Pedido pedidoSalvo = pedidoRepository.save(pedido);

            return ResponseEntity.ok(Map.of(
                    "pedidoId", pedidoSalvo.getId(),
                    "statusPagamento", pedidoSalvo.getStatusPagamento(),
                    "statusPedido", pedidoSalvo.getStatusPedido(),
                    "mensagem", "Pagamento mock recusado"
            ));
        }

        return ResponseEntity.badRequest().body(Map.of(
                "erro", "Status de pagamento inválido",
                "valoresAceitos", "APROVADO ou RECUSADO"
        ));
    }
}
