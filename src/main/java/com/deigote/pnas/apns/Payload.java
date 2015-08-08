package com.deigote.pnas.apns;

import com.notnoop.apns.APNS;
import com.notnoop.apns.PayloadBuilder;

import java.util.List;
import java.util.Map;

public class Payload {

   // Used by Jackson
   private Payload() {
      this(null, null, null, null, null, null);
   }

   public Payload(
      String body, Integer badge, Map<String, String> customFields,
      String localizedKey, List<String> localizedArguments, String actionKey
   ) {
      this.body = body;
      this.badge = badge;
      this.customFields = customFields;
      this.localizedKey = localizedKey;
      this.localizedArguments = localizedArguments;
      this.actionKey = actionKey;
   }

   protected final String body;
   protected final Integer badge;
   protected final Map<String, String> customFields;
   protected final String localizedKey;
   protected final List<String> localizedArguments;
   protected final String actionKey;

   String getPayload() {
      PayloadBuilder builder = APNS.newPayload();
      if (body != null) builder.alertBody(body);
      if (badge != null) builder.badge(badge);
      if (customFields != null) builder.customFields(customFields);
      if (localizedKey != null) builder.localizedKey(localizedKey);
      if (localizedArguments != null) builder.localizedArguments(localizedArguments);
      if (actionKey != null) builder.actionKey(actionKey);
      return builder.build();
   }

   @Override
   public String toString() {
      return "Payload{" +
         (body != null ? "body='" + body + '\'' : "") +
         (badge != null ? " badge=" + badge : "") +
         (customFields != null ? " customFields=" + customFields : "") +
         (localizedKey != null ? " localizedKey='" + localizedKey : "") +
         (localizedArguments != null ? " localizedArguments=" + localizedArguments : "") +
         (actionKey != null ? " actionKey='" + actionKey : "") +
         '}';
   }

}
