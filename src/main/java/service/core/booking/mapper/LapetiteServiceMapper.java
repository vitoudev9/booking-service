package service.core.booking.mapper;

import org.mapstruct.Mapper;
import service.core.booking.data.ServiceData;
import service.core.booking.model.LapetiteService;

@Mapper(componentModel = "spring")
public interface LapetiteServiceMapper {
    ServiceData toDTO(LapetiteService service);
}
