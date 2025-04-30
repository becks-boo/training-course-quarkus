package io.stein.boundary;

import io.stein.domain.Customer;
import io.stein.domain.CustomerState;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class CustomerDtoMapper {

    public CustomerDto map(Customer source) {
        if (source == null) {
            return null;
        }

        var target = new CustomerDto();
        target.setUuid(source.getUuid());
        target.setName(source.getName());
        target.setBirthdate(source.getBirthdate());
        target.setState(mapState(source.getState()));

        return target;
    }

    public String mapState(CustomerState state) {
        return null == state ? null : switch (state) {
            case ACTIVE -> "active";
            case LOCKED -> "locked";
            case DISABLED -> "disabled";
        };
    }

    public Customer map(CustomerDto source) {
        if (source == null) {
            return null;
        }

        var target = new Customer();
        target.setUuid(source.getUuid());
        target.setName(source.getName());
        target.setBirthdate(source.getBirthdate());
        target.setState(mapState(source.getState()));

        return target;
    }

    public CustomerState mapState(String state) {
        return null == state ? null : switch (state) {
            case "active" -> CustomerState.ACTIVE;
            case "locked" -> CustomerState.LOCKED;
            case "disabled" -> CustomerState.DISABLED;
            default -> throw new BadRequestException();
        };
    }
}
