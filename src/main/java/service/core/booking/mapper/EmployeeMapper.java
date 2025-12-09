package service.core.booking.mapper;

import org.mapstruct.Mapper;
import service.core.booking.data.EmployeeData;
import service.core.booking.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeData toDTO(Employee employee);
}
