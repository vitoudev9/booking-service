package service.core.booking.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperUtil {

    public static final ObjectMapper MAPPER = customMapper();

    private static ObjectMapper customMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    public static String toJson(Object obj) throws Exception {
        return MAPPER.writeValueAsString(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        return MAPPER.readValue(json, clazz);
    }

    public static <D> D map(Object source, Class<D> targetClass) {
        return MAPPER.convertValue(source, targetClass);
    }
}
