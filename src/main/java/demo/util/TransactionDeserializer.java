package demo.util;

import demo.model.Transaction;
import lombok.val;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;

/**
 *
 * Simple wrapper for {@link StringDeserializer} for {@link Transaction}
 * Using {@link JsonConverter<Transaction>} it converts json to transaction
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 *
 * @see com.fasterxml.jackson.databind.ObjectMapper
 * @see Transaction
 * @see JsonConverter
 */
public class TransactionDeserializer implements Deserializer<Transaction> {
    private static StringDeserializer stringDeserializer = new StringDeserializer();
    private static JsonConverter<Transaction> converter = new JsonConverter<>(Transaction.class);

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        stringDeserializer.configure(map, b);
    }

    @Override
    public Transaction deserialize(String topic, byte[] bytes) {
        val json = stringDeserializer.deserialize(topic, bytes);
        return converter.fromJson(json);
    }

    @Override
    public void close() {
        stringDeserializer.close();
    }
}
