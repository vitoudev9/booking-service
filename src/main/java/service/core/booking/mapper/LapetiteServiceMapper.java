package service.core.booking.mapper;

import org.mapstruct.Mapper;
import service.core.booking.data.LapetiteServiceData;
import service.core.booking.model.LapetiteService;

@Mapper(componentModel = "spring")
public interface LapetiteServiceMapper {
    LapetiteServiceData toDTO(LapetiteService service);
}
