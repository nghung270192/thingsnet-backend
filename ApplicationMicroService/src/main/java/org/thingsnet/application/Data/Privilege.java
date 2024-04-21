package org.thingsnet.application.Data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thingsnet.application.Data.id.UUIDBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class Privilege extends UUIDBase {
    private String name;
}
