package com.deigote.pnas.gcm;

import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.io.IOException;

public class GcmMessage {

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

   public Result send() throws IOException {
      return new Sender(apiKey).send(
         payload.getMessage(), registrationId, retries == null ? 0 : retries
      );
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
