package org.thingsnet.application.Data;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseData<I> {
    protected I id;
    protected long createdTime;
}
