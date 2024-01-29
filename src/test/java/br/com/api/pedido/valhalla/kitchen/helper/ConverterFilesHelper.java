package br.com.api.pedido.valhalla.kitchen.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConverterFilesHelper {
    public static <T> T convertJsonToObject(String filename, Class<T> classFile) throws IOException {
        URL url = ChangeSetPersister.NotFoundException.class.getClassLoader().getResource("payload/" + filename);

        File file = null;

        if (url != null)
            file = new File(url.getFile());

        if (file == null)
            throw new FileNotFoundException("Arquivo não encontrado");

        String payloadString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(payloadString, classFile);
    }
}
