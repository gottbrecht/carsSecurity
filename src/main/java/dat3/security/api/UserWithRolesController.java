package dat3.security.api;

import dat3.security.dto.UserWithRolesRequest;
import dat3.security.dto.UserWithRolesResponse;
import dat3.security.service.UserWithRolesService;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static dat3.security.api.UserWithRoleController.DEFAULT_ROLE_TO_ASSIGN;
import static dat3.security.entity.Role.fromString;
import static org.springframework.context.annotation.Role.*;


@RestController
@RequestMapping("/api/user-with-role")
public class UserWithRolesController {

        //Take care with this. If no role is required for new users, add null as the value below
    //static Role DEFAULT_ROLE_TO_ASSIGN = Role.USER;

    UserWithRolesService userWithRoleService;

        public UserWithRolesController(UserWithRolesService userWithRoleService) {
            this.userWithRoleService = userWithRoleService;
        }

        //Anonymous users can call this. Set DEFAULT_ROLE_TO_ASSIGN to null if no role should be added
        @PostMapping
        public UserWithRolesResponse addUserWithRoles(@RequestBody UserWithRolesRequest request) {
            return userWithRoleService.addUserWithRole (String.valueOf(request), DEFAULT_ROLE_TO_ASSIGN);
        }

        //Take care with this. This should NOT be something everyone can call
        @PreAuthorize("hasAuthority('ADMIN')")
        @PatchMapping("/add-role/{username}/{role}")
        public UserWithRolesResponse addRole(@PathVariable String username, @PathVariable String role) {
            return userWithRoleService.addRole(Role, fromString(role));
        }
        //Take care with this. This should NOT be something everyone can call
        @PreAuthorize("hasAuthority('ADMIN')")
        @PatchMapping("/remove-role/{username}/{role}")
        public UserWithRolesResponse removeRole(@PathVariable String username, @PathVariable String role) {
            return userWithRoleService.removeRole(username, fromString(role));
        }
    }