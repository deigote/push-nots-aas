package com.deigote.pnas.gcm;

import com.deigote.pnas.Message;
import com.deigote.pnas.PushException;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.io.IOException;

public class GcmMessage implements Message<Result> {

   private GcmMessage() {
      this(null, null, null, null);
   }

   public GcmMessage(String apiKey, String registrationId, Payload payload, Integer retries) {
      this.apiKey = apiKey;
      this.registrationId = registrationId;
      this.payload = payload;
      this.retries = retries;
   }

   private final String apiKey, registrationId;
   private final Payload payload;
   private final Integer retries;

   public Result send() throws PushException {
      try {
         return new Sender(apiKey).send(
            payload.getMessage(), registrationId, retries == null ? 0 : retries
         );
      } catch (IOException e) {
         throw new PushException(this, e);
      }
   }

   @Override
   public String toString() {
      return "PushNotification{" +
         "apiKey='" + (apiKey != null ? "xxx" : "null") + '\'' +
         ", registrationId='" + (registrationId != null ? "xxx" : "null") + '\'' +
         ", payload=" + payload +
         ", retries=" + retries +
         '}';
   }


}
