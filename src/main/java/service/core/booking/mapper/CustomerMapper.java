package service.core.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.core.booking.data.CustomerData;
import service.core.booking.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "existing", ignore = true)
    CustomerData toDTO(Customer customer);
}
