package domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="version", defaultImpl = ProductV1.class)
@JsonSubTypes({
        @Type(name="v1", value= ProductV1.class),
        @Type(name="v2", value= ProductV2.class)
})
public interface Product {
}
