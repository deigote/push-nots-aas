package com.deigote.pnas.apns;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;

import java.util.Date;

public class ApnsMessage {

   // Used by Jackson
   private ApnsMessage() {
      this(null, null, null, null, null);
   }

   public ApnsMessage(
      Credentials credentials,
      Payload payload,
      String deviceToken,
      Integer ttlInSeconds,
      Boolean production
   ) {
      this.credentials = credentials;
      this.payload = payload;
      this.deviceToken = deviceToken;
      this.ttlInSeconds = ttlInSeconds;
      this.production = production;
   }

   private final Credentials credentials;
   private final Payload payload;
   private final String deviceToken;
   private final Integer ttlInSeconds;
   private final Boolean production;
   private ApnsService apnsService;

   private ApnsService getApnsService() {
      if (apnsService == null) {
         apnsService = APNS.newService()
            .withCert(credentials.getCertificate(),credentials.password)
            .withAppleDestination(production)
            .build();
      }
      return apnsService;
   }

   private Date getExpiryDate() {
      return new Date(new Date().getTime() + (1000 * ttlInSeconds));
   }

   public ApnsNotification send() {
      return ttlInSeconds != null && ttlInSeconds > 0 ?
         getApnsService().push(deviceToken, payload.getPayload(), getExpiryDate()) :
         getApnsService().push(deviceToken, payload.getPayload());
   }

   @Override
   public String toString() {
      return "Message{" +
         "production=" + production +
         ", ttlInSeconds=" + ttlInSeconds +
         ", deviceToken='" + deviceToken + '\'' +
         ", payload=" + payload +
         '}';
   }
}
