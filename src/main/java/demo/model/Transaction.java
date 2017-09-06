package demo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import demo.util.SimpleDateDeserializer;
import demo.util.SimpleDateSerializer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Pojo object of shop transaction
 *
 *
 * @author Poshivalov N.A.
 * @since 05.09.2017.
 */
@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    private Long id;
    private Long stockId;
    private Integer quantity;

    @JsonDeserialize(using = SimpleDateDeserializer.class)
    @JsonSerialize(using = SimpleDateSerializer.class)
    private LocalDateTime invoiceDate;

    private Double price;
    private Long customerId;

}
