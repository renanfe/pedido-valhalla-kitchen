package br.com.api.pedido.valhalla.kitchen.adapter.driver;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ProdutoForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.services.ProdutoService;
import br.com.api.pedido.valhalla.kitchen.core.domain.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(final ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos(final @RequestParam(required = false) String categoria) {
        if (categoria == null) {
            return ResponseEntity.ok(produtoService.buscarTodosProdutos());
        }
        return ResponseEntity.ok(produtoService.buscarTodosProdutosPorCategoria(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaProdutoPorId(final @PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> criarProduto(final @RequestBody ProdutoForm produtoForm, final UriComponentsBuilder uriBuilder) {
        Produto produto = produtoService.criarProduto(produtoForm);

        String novaUri = uriBuilder.path("/{id}").buildAndExpand(produto.getId()).toUriString();
        return ResponseEntity.created(UriComponentsBuilder.fromUriString(novaUri).build().toUri())
                .body("Produto criado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProdutoPorId(final @PathVariable Long id, final @RequestBody ProdutoForm produtoForm) {
        return produtoService.editarProdutoPorId(id, produtoForm).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarProduto(final @PathVariable Long id) {
        if(produtoService.inativarProduto(id).isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
