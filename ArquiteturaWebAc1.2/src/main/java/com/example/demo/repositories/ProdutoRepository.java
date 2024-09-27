package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Produto;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByPrecoGreaterThan(Double valor);
    List<Produto> findByPrecoLessThanEqual(Double valor);
    List<Produto> findByNomeStartingWith(String nome);
}