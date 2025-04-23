package io.stein;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Customer {

    private UUID uuid;
    private String name;
    private LocalDate birthdate;
    private String state;

}
