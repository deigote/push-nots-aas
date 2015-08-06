package com.deigote.pnas.apns;

import com.google.common.collect.ImmutableList;
import com.notnoop.apns.APNS;
import com.notnoop.apns.PayloadBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Payload {

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

   final String body;
   final Integer badge;
   final Map<String, String> customFields;
   final String localizedKey;
   final List<String> localizedArguments;
   final String actionKey;

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

}
