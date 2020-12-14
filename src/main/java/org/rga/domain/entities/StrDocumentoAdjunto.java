package org.rga.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StrDocumentoAdjunto {
    private String idArchiv;
    private String idUsuario;
    private Integer codMedia;
    private Integer codOfici;
    public StrDocumentoAdjunto(){}
}
