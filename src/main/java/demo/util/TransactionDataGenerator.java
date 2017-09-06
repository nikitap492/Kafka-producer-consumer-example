package demo.util;

import demo.model.Transaction;
import lombok.val;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;
import static java.util.stream.Collectors.*;

/**
 *
 * Data generator creates random data of {@link Transaction}
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 *
 * @see Transaction
 */
public class TransactionDataGenerator {

    private final Random r;

    public TransactionDataGenerator() {
        this.r = new Random(System.currentTimeMillis());
    }

    public List<Transaction> generate(int num){
        return  IntStream.range(0, num)
                .mapToObj(this::create)
                .collect(toList());
    }

    private Transaction create(int id) {
        val q = abs(r.nextInt(200));
        return Transaction.builder()
                .id((long) id)
                .customerId(abs(r.nextLong()))
                .invoiceDate(LocalDateTime.now().plusSeconds(id))
                .stockId((long) abs(r.nextInt(10)))
                .quantity(q)
                .price(abs(r.nextDouble() * r.nextInt(50)) * q)
                .build();
    }


}
