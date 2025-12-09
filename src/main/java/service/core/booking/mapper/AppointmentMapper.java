package service.core.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.core.booking.data.AppointmentResponseData;
import service.core.booking.model.Appointment;

@Mapper(componentModel = "spring",
        uses = { CustomerMapper.class, EmployeeMapper.class, LapetiteServiceMapper.class })
public interface AppointmentMapper {

    @Mapping(target = "startTime", source = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "service", source = "lapetiteService")
    AppointmentResponseData toDTO(Appointment appointment);

}
