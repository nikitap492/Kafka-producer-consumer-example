package demo.service;

import demo.model.Transaction;
import demo.util.TransactionSerializer;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Simple kafka producer
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 *
 * @see KafkaProducer
 */
public class SimpleProducer {

    private final static String TOPIC = "e-comm-transactions";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String PRODUCER_NAME = "SimpleProducer";
    private final KafkaProducer<String, Transaction> producer;

    /**
     * Set producer properties
     */
    public SimpleProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, PRODUCER_NAME);
        this.producer = new KafkaProducer<>(props, new StringSerializer(), new TransactionSerializer());
    }

    /**
     * Produces data
     * @param data is list of random created transactions
     */
    public void produce(List<Transaction> data) {
        try {
            data.forEach(this::send);
        } finally {
            producer.flush();
            producer.close();
        }
    }

    /**
     * Send to kafka one item of data
     * @param transaction is transaction what will be sent
     */
    @SneakyThrows
    private void send(Transaction transaction) {
        producer.send(new ProducerRecord<>(TOPIC, transaction)).get();
    }

}
