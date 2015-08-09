package com.deigote.pnas;

import com.deigote.pnas.apns.ApnsMessage;
import com.deigote.pnas.gcm.GcmMessage;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
   @Type(name = "gcm", value = GcmMessage.class),
   @Type(name = "apns", value = ApnsMessage.class)
})
public interface Message<R> {
   R send() throws PushException;
}
