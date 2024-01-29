package br.com.api.pedido.valhalla.kitchen.helper;

import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ClienteForm;
import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;

import java.io.IOException;

public class ClienteHelper {
    public static Cliente gerarCliente () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("cliente-completo.json", Cliente .class);
    }

    public static ClienteForm gerarClienteForm () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("cliente-form.json", ClienteForm.class);
    }
}
