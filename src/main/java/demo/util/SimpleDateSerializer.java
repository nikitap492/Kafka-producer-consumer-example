package demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@link LocalDateTime} serializer
 * Uses pattern 'dd/MM/yyyy HH:mm:ss' (f.e. 10/02/2017 23:01:34)
 * It needs for serialization of {@link demo.model.Transaction#invoiceDate}
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 */
public class SimpleDateSerializer extends JsonSerializer<LocalDateTime> {
    private static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DATE_TIME_FORMAT.format(localDateTime));
    }
}
