package edu.unisbana.tyvs_tienda_ropa.application.domain.rq;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {
    private String name;
    private String email;
    private String address;
}