package edu.unisbana.tyvs_tienda_ropa.application.domain.rq;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {
    private List<Long> productIds;
    private Double totalAmount;
    private String status;
}
