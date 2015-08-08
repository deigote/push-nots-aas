package com.deigote.pnas;

import com.deigote.pnas.apns.Message;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.exec.Blocking;
import ratpack.jackson.Jackson;
import ratpack.registry.RegistrySpec;
import ratpack.server.RatpackServer;

public class PushNotificationsService {

   public static void main(String... args) throws Exception {
      RatpackServer.start(server ->
         server
            .registryOf(PushNotificationsService::registerJacksonMapper)
            .handlers(chain ->
               chain.post("apns", ctx ->
                  ctx.parse(Message.class)
                     .wiretap(messageResult -> System.out.println(messageResult.getValue()))
                     .flatMap(message -> Blocking.get(() -> message.send() ))
                     .then(notification -> ctx.render(notification))
               ).post("gcm",
                  ctx -> ctx.render("Hello GCM!")
               )
            )
      );
   }

   private static RegistrySpec registerJacksonMapper(RegistrySpec registrySpec) {
      ObjectMapper objectMapper = new ObjectMapper()
         .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
      return Jackson.Init.register(registrySpec, objectMapper, objectMapper.writer());
   }

}
