package com.example.tarefaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tarefaapi.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}