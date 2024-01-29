package br.com.api.pedido.valhalla.kitchen.adapter.driver;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ClienteForm;
import br.com.api.pedido.valhalla.kitchen.core.applications.services.ClienteService;
import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscaClientePorCpf(final @PathVariable String cpf) {
        return clienteService.buscaClienteCpf(cpf)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> criaCliente(final @RequestBody ClienteForm clienteForm, final UriComponentsBuilder uriBuilder) {
        Cliente cliente = clienteService.criarCliente(clienteForm);
        String novaUri = uriBuilder.path("/v1/clientes/{id}").buildAndExpand(cliente.getCpf()).toUriString();
        return ResponseEntity.created(UriComponentsBuilder.fromUriString(novaUri).build().toUri())
                .body("Cliente criado com sucesso!");
    }

}
