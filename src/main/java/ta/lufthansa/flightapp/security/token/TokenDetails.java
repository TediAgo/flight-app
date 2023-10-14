package ta.lufthansa.flightapp.security.token;

import lombok.Data;
import ta.lufthansa.flightapp.user.model.enums.Role;

@Data
public final class TokenDetails {

    private final String id;
    private final String email;
    private final Role role;
}
