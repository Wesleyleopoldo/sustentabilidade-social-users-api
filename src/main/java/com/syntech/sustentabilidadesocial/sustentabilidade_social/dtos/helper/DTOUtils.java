package com.syntech.sustentabilidadesocial.sustentabilidade_social.dtos.helper;

import java.lang.reflect.Field; // importação do Field que reflete o atributo...
import java.util.HashMap; // importação do HashMap que é uma implementação da interface Map...
import java.util.Map; // importação da interface Map...

public class DTOUtils {

    // Metodo que limpa o dto de campos nulos...
    public static Map<String, Object> cleanDTO(Object dto) throws IllegalAccessException {
        Map<String, Object> result = new HashMap<>();
        for (Field field: dto.getClass().getDeclaredFields()) {
            field.setAccessible(true); // torna o atributo acessivel mesmo se for privado...
            try {
                Object value = field.get(dto); // aqui ele basicamente fala pegue o valor do field refletido no momento nesse objeto...
                if (value != null) {
                    result.put(field.getName(), value); // armazena chave e valor no dicionario ou hashmap...
                }
            } catch (IllegalAccessException exception) {
                throw new IllegalAccessException("Não foi possível criar o dto, algum campo não pode ser acessado");
            }
        }
        return result;
    }
}