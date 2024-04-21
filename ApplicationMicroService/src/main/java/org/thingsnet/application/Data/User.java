package org.thingsnet.application.Data;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import lombok.*;
import org.thingsnet.application.Data.id.UUIDBase;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends UUIDBase {
    private long createTime;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String additionalInfo;

}
