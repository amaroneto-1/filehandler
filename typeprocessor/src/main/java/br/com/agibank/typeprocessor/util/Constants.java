package br.com.agibank.typeprocessor.util;

public class Constants {
    public static final String ID_VENDEDOR = "001";
    public static final String ID_CLIENTE = "002";
    public static final String ID_VENDA = "003";

    public static final class ERROR{
        public static final String ROW_INVALID_FORMAT = "Formato inválido - Erro ao subdividir elementos internos da linha";
        public static final String ROW_INVALID_FORMAT_REGEX = "Formato inválido - Erro ao detectar padrão da linha";
    }

    public static final class RESULTADO{
        public static final String VENDA_MAIS_CARA = "venda mais cara";
        public static final String PIOR_VENDEDOR = "Pior vendedor";
        public static final String NUMERO_VENDEDORES = "vendedor";
        public static final String NUMERO_CLIENTES = "cliente";
    }

}
