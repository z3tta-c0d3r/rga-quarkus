package org.rga.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.rga.domain.entities.StrDocumentoAdjunto;
import org.rga.domain.entities.StrIdCliente;
import org.rga.domain.entities.StrIdPoliza;
import org.rga.domain.entities.StrIdSiniestro;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDomain {
    private String tipoEnvio;
    private String tipoEmail;
    private StrIdCliente strIdCliente;
    private StrIdPoliza strIdPoliza;
    private StrIdSiniestro strIdSiniestro;
    private String textoSms;
    private String asuntoEmail;
    private String textoEmail;
    private String fechaEnvio;
    private String numTelefono;
    private String direccionEmail;
    private String rolDestinatario;
    private StrDocumentoAdjunto strDocumentoAdjunto;
    public UserDomain(){}
}