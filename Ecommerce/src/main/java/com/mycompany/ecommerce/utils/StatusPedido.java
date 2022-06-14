package com.mycompany.ecommerce.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Piaia
 */
public enum StatusPedido {

    EM_PROCESSAMENTO {
        @Override
        public int getStatusCodigo() {
            return 1;
        }

        @Override
        public String getStatusLabel() {
            return "Em Processamento";
        }
    }, PROCESSADO {
        @Override
        public int getStatusCodigo() {
            return 2;
        }

        @Override
        public String getStatusLabel() {
            return "Processado";
        }
    }, ENVIADO {
        @Override
        public int getStatusCodigo() {
            return 3;
        }

        @Override
        public String getStatusLabel() {
            return "Enviado";
        }
    }, ENTREGUE {
        @Override
        public int getStatusCodigo() {
            return 4;
        }

        @Override
        public String getStatusLabel() {
            return "Entregue";
        }
    };

    public abstract int getStatusCodigo();

    public abstract String getStatusLabel();

    public static StatusPedido getStatusPorCodigo(int cod) {
        for (StatusPedido status : values()) {
            if (status.getStatusCodigo() == cod) {
                return status;
            }
        }
        return null;
    }

    public static List<StatusPedido> getStatusDisponiveis() {
        List<StatusPedido> status = new ArrayList<>(Arrays.asList(values()));
        return status;
    }

}
