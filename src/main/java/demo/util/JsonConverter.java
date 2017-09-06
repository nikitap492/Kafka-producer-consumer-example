package demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 *
 * This class is used for converting object to json and vice versa
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 *
 * @see ObjectMapper
 */
@RequiredArgsConstructor(staticName = "of")
class JsonConverter<T> {
    private final Class<T> clazz;
    private final ObjectMapper mapper;

    JsonConverter(Class<T> clazz) {
        this.clazz = clazz;
        this.mapper = new ObjectMapper();
    }


    @SneakyThrows
    String toJson(T instance){
        return mapper.writeValueAsString(instance);
    }

    @SneakyThrows
    T fromJson(String json){
        return mapper.readValue(json, clazz);
    }

}
