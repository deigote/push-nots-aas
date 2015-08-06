package com.deigote.pnas.apns;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.EnhancedApnsNotification;

import java.util.Date;

public class Message {

   public Message(
      Credentials credentials, Payload payload, String deviceToken,
      Integer ttlInSeconds, Boolean production
   ) {
      this.credentials = credentials;
      this.payload = payload;
      this.deviceToken = deviceToken;
      this.ttlInSeconds = ttlInSeconds;
      this.production = production;
   }

   final Credentials credentials;
   final Payload payload;
   final String deviceToken;
   final Integer ttlInSeconds;
   final Boolean production;
   private ApnsService apnsService;

   private ApnsService getApnsService() {
      if (apnsService == null) {
         apnsService = APNS.newService()
            .withCert(credentials.getCertificate(), credentials.password)
            .withAppleDestination(production)
            .build();
      }
      return apnsService;
   }

   private ApnsNotification send() {
      return ttlInSeconds != null && ttlInSeconds > 0 ?
         getApnsService().push(deviceToken, payload.getPayload(), getExpiryDate()) :
         getApnsService().push(deviceToken, payload.getPayload());
   }

   private Date getExpiryDate() {
      return new Date(new Date().getTime() + (1000 * ttlInSeconds));
   }
}
