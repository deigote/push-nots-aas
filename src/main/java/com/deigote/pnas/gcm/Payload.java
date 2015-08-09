package com.deigote.pnas.gcm;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

import java.util.Map;

public class Payload {

   private Payload() {
      this(null, null, null, null);
   }

   public Payload(
      Map<String, String> data, String collapseKey, Boolean delayWhileIdle, Integer ttlInSeconds
   ) {
      this.data = data;
      this.collapseKey = collapseKey;
      this.delayWhileIdle = delayWhileIdle;
      this.ttlInSeconds = ttlInSeconds;
   }

   private final Map<String, String> data;
   private final String collapseKey;
   private final Boolean delayWhileIdle;
   private final Integer ttlInSeconds;

   Message getMessage() {
      Message.Builder builder = new Message.Builder();
      if (data != null) builder.setData(data);
      if (collapseKey != null) builder.collapseKey(collapseKey);
      if (delayWhileIdle != null) builder.delayWhileIdle(delayWhileIdle);
      if (ttlInSeconds != null) builder.timeToLive(ttlInSeconds);
      return builder.build();
   }

   @Override
   public String toString() {
      return "Payload{" +
         "data=" + data +
         ", collapseKey='" + collapseKey + '\'' +
         ", delayWhileIdle=" + delayWhileIdle +
         ", ttlInSeconds=" + ttlInSeconds +
         '}';
   }
}
