package br.com.api.pedido.valhalla.kitchen.adapter.driver;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.PedidoForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.services.PedidoService;
import br.com.api.pedido.valhalla.kitchen.core.domain.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTodosPedidos() {
        return ResponseEntity.ok(pedidoService.buscarTodosPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(final @PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(final @RequestBody PedidoForm pedidoForm, final UriComponentsBuilder uriBuilder) {
        Pedido pedido = pedidoService.criarPedido(pedidoForm);

        String novaUri = uriBuilder.path("/{id}").buildAndExpand(pedido.getId()).toUriString();

        return ResponseEntity.created(UriComponentsBuilder.fromUriString(novaUri).build().toUri()).body(pedido);
    }

}
