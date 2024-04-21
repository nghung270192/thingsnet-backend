package org.thingsnet.application.Data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thingsnet.application.Data.id.UUIDBase;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends UUIDBase {
    private String name;
    Set<Privilege> privileges;
}
