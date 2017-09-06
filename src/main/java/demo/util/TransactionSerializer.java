package demo.util;

import demo.model.Transaction;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;

/**
 *
 * Simple wrapper for {@link StringSerializer} for {@link Transaction}
 * Using {@link JsonConverter<Transaction>} it converts transaction to json
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 *
 * @see com.fasterxml.jackson.databind.ObjectMapper
 * @see Transaction
 * @see JsonConverter
 */
public class TransactionSerializer implements Serializer<Transaction> {

    private StringSerializer stringSerializer = new StringSerializer();
    private JsonConverter<Transaction> converter = new JsonConverter<>(Transaction.class);


    @Override
    public void configure(Map<String, ?> map, boolean b) {
        stringSerializer.configure(map, b);
    }

    @Override
    public byte[] serialize(String topic, Transaction transaction) {
        return stringSerializer.serialize(topic, converter.toJson(transaction));
    }

    @Override
    public void close() {
        stringSerializer.close();
    }
}
