package com.example.ProjetoModuloBD.service;

import com.example.ProjetoModuloBD.dto.CompraRequest;
import com.example.ProjetoModuloBD.dto.CompraResponse;
import com.example.ProjetoModuloBD.exceptions.BadRequest;
import com.example.ProjetoModuloBD.model.Compra;
import com.example.ProjetoModuloBD.model.CompraProduto;
import com.example.ProjetoModuloBD.model.CompraProdutoKey;
import com.example.ProjetoModuloBD.model.Produto;
import com.example.ProjetoModuloBD.repository.CompraProdutoRepository;
import com.example.ProjetoModuloBD.repository.CompraRepository;
import com.example.ProjetoModuloBD.repository.ProdutoRepository;
import com.example.ProjetoModuloBD.repository.specification.CompraSpecification;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoService produtoService;
    private final CompraProdutoRepository compraProdutoRepository;

    public Page<CompraResponse> listByCPF(String cpf, Pageable pageable) {
        Specification<Compra> specification = Specification.where(null);
        if (cpf != null) {
            specification = Specification.where(CompraSpecification.filterOneByCpf(cpf));
        }
        return compraRepository
                .findAll(specification, pageable)
                .map(CompraResponse::convert);
    }

    public CompraResponse createCompra(CompraRequest compraRequest) throws BadRequest {
        double sum_values = 0.0;

        Compra compra = new Compra();
        compra.setData_compra(compraRequest.getData());
        compra.setCpf(compraRequest.getCpf());
        compra.setValor_total_compra(0.0);

        compraRepository.save(compra);

        for (Map.Entry<String,Integer> entry : compraRequest.getProdutos().entrySet()){
            Optional<Produto> produto = produtoService.findByCodigo(entry.getKey());

            if (produto.isEmpty()){
                compraProdutoRepository.deleteAll(compra.getProdutos());
                compraRepository.delete(compra);
                throw new BadRequest("Produto não encontrado");
            }
            if (produto.get().getQtde_disponivel() < entry.getValue()) {
                compraProdutoRepository.deleteAll(compra.getProdutos());
                compraRepository.delete(compra);
                throw new BadRequest("Quantidade indisponível");
            }

            CompraProdutoKey key = new CompraProdutoKey();
            key.setIdCompra(compra.getId());
            key.setIdProduto(produto.get().getId());

            CompraProduto compraProduto = new CompraProduto();
            compraProduto.setCompra(compra);
            compraProduto.setProduto(produto.get());
            compraProduto.setQuantidade(entry.getValue());
            compraProduto.setCompraProdutoKey(key);

            compraProdutoRepository.save(compraProduto);
            compra.getProdutos().add(compraProduto);

            sum_values += produto.get().getPreco()*entry.getValue();
        }

        produtoService.updateQuantity(compraRequest.getProdutos());
        compra.setValor_total_compra(sum_values);

        if(compra.getValor_total_compra() == null){
            throw new BadRequest("O campo produtos deve ser preenchido.");
        }

        compraRepository.save(compra);

        return CompraResponse.convert(compra);
    }

}
