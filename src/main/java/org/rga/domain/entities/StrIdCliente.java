package org.rga.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StrIdCliente {
    private String tipoDocumento;
    private String idCliente;
    public StrIdCliente(){}
}
