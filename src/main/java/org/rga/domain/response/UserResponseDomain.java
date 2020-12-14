package org.rga.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.rga.domain.entities.StrDocumentoAdjunto;
import org.rga.domain.entities.StrIdCliente;
import org.rga.domain.entities.StrIdPoliza;
import org.rga.domain.entities.StrIdSiniestro;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDomain {
//    private Integer id;
//    private String email;
//    private String password;
//    private String createDate;
//    private String updatedAt;
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
    public UserResponseDomain(){}
}