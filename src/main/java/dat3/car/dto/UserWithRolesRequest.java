package dat3.car.dto;

import lombok.*;
import dat3.security.entity.UserWithRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserWithRolesRequest {
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String email;
    }

}
