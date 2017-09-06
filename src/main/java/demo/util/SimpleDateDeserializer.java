package demo.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@link LocalDateTime} deserializer
 * It convert string with format 'dd/MM/yyyy HH:mm:ss' to {@link LocalDateTime}
 * It needs for deserialization of {@link demo.model.Transaction#invoiceDate}
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 */
public class SimpleDateDeserializer extends JsonDeserializer<LocalDateTime> {

    private static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return LocalDateTime.parse(jsonParser.getText(), DATE_TIME_FORMAT);
    }
}
