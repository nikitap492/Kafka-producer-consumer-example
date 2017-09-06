package demo.service;

import demo.model.Transaction;
import demo.util.TransactionDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;
import java.util.stream.StreamSupport;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 *
 * Simple kafka producer
 * Implements runnable interface for creation many items because kafka consumer is not thread-safe
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 *
 * @see SimpleConsumerGroupFactory
 * @see org.apache.kafka.clients.producer.KafkaProducer
 */
public class SimpleConsumerRunnable implements Runnable {

    private final static String TOPIC = "e-comm-transactions";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    private final KafkaConsumer<String, Transaction> consumer;
    private final int number;

    /**
     * Set configuration for kafka consumer
     * @param number is order number of kafka consumer
     */
    SimpleConsumerRunnable(int number){
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(GROUP_ID_CONFIG, "test");
        props.put(ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        this.consumer = new KafkaConsumer<>(props, new StringDeserializer(), new TransactionDeserializer());
        this.number = number;
    }


    /**
     * Create infinite loop which will print consumed message to console
     */
    @Override
    public void run() {
        consumer.subscribe(Collections.singleton(TOPIC));
        Thread.currentThread().setName("Consumer #" + number);
        while (true) {
            consumer.poll(100).forEach(record ->
                System.out.printf("#%d record: %s", number, record.value())
            );
        }
    }
}
