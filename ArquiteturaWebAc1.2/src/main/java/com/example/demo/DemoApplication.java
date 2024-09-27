package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.Categoria;
import com.example.demo.models.Produto;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ProdutoRepository;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria categoria1 = new Categoria(null, "Eletrônicos", null);
        Categoria categoria2 = new Categoria(null, "Vestuário", null);
        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));

        Produto produto1 = new Produto(null, "TV", 3000.00, categoria1);
        Produto produto2 = new Produto(null, "Computador", 5000.00, categoria1);
        Produto produto3 = new Produto(null, "Camiseta", 50.00, categoria2);

        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

        System.out.println("\nProdutos com preço maior que 1000:");
        produtoRepository.findByPrecoGreaterThan(1000.00).forEach(System.out::println);

        System.out.println("\nProdutos com preço menor ou igual a 50:");
        produtoRepository.findByPrecoLessThanEqual(50.00).forEach(System.out::println);

        System.out.println("\nProdutos que começam com 'C':");
        produtoRepository.findByNomeStartingWith("C").forEach(System.out::println);

        System.out.println("\nCategorias que começam com 'E':");
        categoriaRepository.findByNomeStartingWith("E").forEach(System.out::println);

        System.out.println("\nCategoria com ID 1 e seus produtos:");
        Categoria categoriaComProdutos = categoriaRepository.categoriaComProdutos(1L);
        System.out.println(categoriaComProdutos);
        categoriaComProdutos.getProdutos().forEach(System.out::println);
    }
}