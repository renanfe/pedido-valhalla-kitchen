package br.com.api.pedido.valhalla.kitchen.helper;

import br.com.api.pedido.valhalla.kitchen.adapter.driven.infra.entity.ClienteEntity;
import br.com.api.pedido.valhalla.kitchen.adapter.driver.form.ClienteForm;
import br.com.api.pedido.valhalla.kitchen.core.domain.Cliente;

import java.io.IOException;

public class ClienteHelper {

    public static ClienteForm gerarClienteForm () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("cliente-form.json", ClienteForm.class);
    }

    public static ClienteEntity gerarClienteEntity () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("cliente-completo.json", ClienteEntity.class);
    }
    public static Cliente gerarClienteRetornoTela () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("cliente-retorno-tela.json", Cliente.class);
    }

    public static Cliente gerarClienteRequest () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("cliente-request.json", Cliente.class);
    }

    public static ClienteEntity gerarClienteRequestEntity () throws IOException {
        return ConverterFilesHelper.convertJsonToObject("cliente-request.json", ClienteEntity.class);
    }
}
