package service.core.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.core.booking.data.AppointmentResponseData;
import service.core.booking.model.Appointment;

@Mapper(componentModel = "spring",
        uses = { CustomerMapper.class, EmployeeMapper.class, LapetiteServiceMapper.class })
public interface AppointmentMapper {

    @Mapping(target = "service", source = "lapetiteService")
    AppointmentResponseData toDTO(Appointment appointment);

}
