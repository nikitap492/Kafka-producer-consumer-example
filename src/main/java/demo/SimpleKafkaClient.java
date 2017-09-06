package demo;

import demo.service.SimpleConsumerGroupFactory;
import demo.service.SimpleProducer;
import demo.util.TransactionDataGenerator;
import lombok.val;

/**
 * This demo application demonstrate how to use kafka clients to push and receive message through kafka
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 */
public class SimpleKafkaClient{


    public static void main(String[] args) throws InterruptedException {
        SimpleConsumerGroupFactory.createAndSubscribe(3);
        val generator = new TransactionDataGenerator();


        while(true){
            val data = generator.generate(10000);
            val producer = new SimpleProducer();
            producer.produce(data);
            Thread.sleep(100);
        }

    }


}
