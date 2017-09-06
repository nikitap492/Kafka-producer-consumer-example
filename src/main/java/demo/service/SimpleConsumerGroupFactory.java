package demo.service;

import java.util.stream.IntStream;

/**
 * Create group of consumer instances
 * One consumer per single thread because kafka consumer is not thread-safe
 *
 * @author Poshivalov N.A.
 * @since 06.09.2017.
 *
 * @see  SimpleConsumerRunnable
 * @see org.apache.kafka.clients.consumer.KafkaConsumer
 */
public class SimpleConsumerGroupFactory {

    /**
     * Create group of consumer instances
     * @param num is number of consumer in group
     */
    public static void createAndSubscribe(int num){
        IntStream.range(0, num).forEach(n ->
                new Thread(new SimpleConsumerRunnable(n)).start()
        );

    }

}
