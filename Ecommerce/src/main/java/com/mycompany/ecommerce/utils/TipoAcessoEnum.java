package com.mycompany.ecommerce.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Piaia
 */
public enum TipoAcessoEnum {

    USUARIO_PUBLICO {
        @Override
        public int getTipoAcessoCodigo() {
            return 1;
        }

        @Override
        public String getTipoAcessoLabel() {
            return "Usuário Público";
        }

    },
    ADMIN {
        @Override
        public int getTipoAcessoCodigo() {
            return 2;
        }

        @Override
        public String getTipoAcessoLabel() {
            return "Administrador";
        }

    },
    CLIENTE {
        @Override
        public int getTipoAcessoCodigo() {
            return 3;
        }

        @Override
        public String getTipoAcessoLabel() {
            return "Cliente";
        }

    };

    public abstract int getTipoAcessoCodigo();

    public abstract String getTipoAcessoLabel();

    public static List<TipoAcessoEnum> getTiposDisponiveisLogin() {
        List<TipoAcessoEnum> tipos = new ArrayList<>();
        for (TipoAcessoEnum tipo : values()) {
            if (!tipo.equals(TipoAcessoEnum.USUARIO_PUBLICO)) {
                tipos.add(tipo);
            }
        }
        return tipos;
    }

    public static TipoAcessoEnum getTipoAcessoPorCodigo(int codigo) {
        for (TipoAcessoEnum tipoAcesso : values()) {
            if (tipoAcesso.getTipoAcessoCodigo() == codigo) {
                return tipoAcesso;
            }
        }
        return null;
    }

}
