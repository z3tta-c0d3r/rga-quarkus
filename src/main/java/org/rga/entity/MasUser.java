package org.rga.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "Mas_User")
public class MasUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Integer id;
//
//    @Column
//    private String email;
//
//    @Column
//    private String password;
//
//    @Column(name = "created_At")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//
//    @Column(name = "updated_At")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date updatedAt;

    @Column
    private String tipoEnvio;

    @Column
    private String tipoEmail;

    @Column
    private String strIdCliente;

    @Column
    private String strIdPoliza;

    @Column
    private String strIdSiniestro;

    @Column
    private String textoSms;

    @Column
    private String asuntoEmail;

    @Column
    private String textoEmail;

    @Column
    private String fechaEnvio;

    @Column
    private String numTelefono;

    @Column
    private String direccionEmail;

    @Column
    private String rolDestinatario;

    @Column
    private String strDocumentoAdjunto;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean send;
}