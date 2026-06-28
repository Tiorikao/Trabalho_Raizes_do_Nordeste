package com.example.tarefaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarefaapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}