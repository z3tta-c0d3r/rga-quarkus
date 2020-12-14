package org.rga.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StrIdSiniestro {
    private String codProducto;
    private String numPoliza;
    private Integer añoEmisionPoliza;
    private Integer añoApertura;
    private String numSiniestro;
    public StrIdSiniestro(){}
}
