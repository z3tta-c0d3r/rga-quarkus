package org.rga.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StrIdPoliza {
    private String codProducto;
    private String numPoliza;
    private Integer añoEmisionPoliza;
    private String tipoDocumento;
    private String idCliente;
    private String rol;
    public StrIdPoliza(){}
}
