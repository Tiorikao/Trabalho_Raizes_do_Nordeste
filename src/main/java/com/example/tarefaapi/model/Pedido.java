package com.example.tarefaapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    private String produto;

    private int quantidade;

    private double valorTotal;

    private String canalPedido;

    private String statusPedido;

    private String statusPagamento;

    public Pedido() {
    }

    @PrePersist
    public void prePersist() {
        if (statusPedido == null || statusPedido.isBlank()) {
            statusPedido = "CRIADO";
        }

        if (statusPagamento == null || statusPagamento.isBlank()) {
            statusPagamento = "AGUARDANDO_PAGAMENTO";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCanalPedido() {
        return canalPedido;
    }

    public void setCanalPedido(String canalPedido) {
        this.canalPedido = canalPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
