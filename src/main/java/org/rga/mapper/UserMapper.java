package org.rga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.rga.domain.request.UserDomain;
import org.rga.domain.response.UserResponseDomain;
import org.rga.entity.MasUser;
import org.rga.mapper.qualifiers.DateConverter;
import org.rga.mapper.qualifiers.ParseDate;
import org.rga.mapper.qualifiers.ParseTimestamp;

@Mapper( uses = DateToTimestamp.class )
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    //@Mapping(target="createdAt", source="createDate", qualifiedBy ={ DateConverter.class, ParseTimestamp.class})
    @Mapping(target="strIdCliente", source="strIdCliente.idCliente")
    @Mapping(target="strIdPoliza", source="strIdPoliza.idCliente")
    @Mapping(target="strIdSiniestro", source="strIdSiniestro.codProducto")
    @Mapping(target="strDocumentoAdjunto", source="strDocumentoAdjunto.idArchiv")
    MasUser UserDomainToUserEntity(UserDomain userDomain);

    //@Mapping(target="createDate", source="createdAt", qualifiedBy ={ DateConverter.class, ParseDate.class})
    @Mapping(target="strIdCliente.idCliente", source="strIdCliente")
    @Mapping(target="strIdPoliza.idCliente", source="strIdPoliza")
    @Mapping(target="strIdSiniestro.codProducto", source="strIdSiniestro")
    @Mapping(target="strDocumentoAdjunto.idArchiv", source="strDocumentoAdjunto")
    UserResponseDomain UserEntityToUserDomain(MasUser masUser);

    //@Mapping(target="createDate", source="createdAt", qualifiedBy ={ DateConverter.class, ParseDate.class})
    @Mapping(target="strIdCliente.idCliente", source="strIdCliente")
    @Mapping(target="strIdPoliza.idCliente", source="strIdPoliza")
    @Mapping(target="strIdSiniestro.codProducto", source="strIdSiniestro")
    @Mapping(target="strDocumentoAdjunto.idArchiv", source="strDocumentoAdjunto")
    UserDomain UserEntityToUserDomainTwilio(MasUser masUser);
}
