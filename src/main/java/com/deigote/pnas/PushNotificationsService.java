package com.deigote.pnas;

import ratpack.server.RatpackServer;

public class PushNotificationsService {

   public static void main(String... args) throws Exception {
      RatpackServer.start(server ->
            server.handlers(chain ->
                  chain.post("apns",
                     ctx -> ctx.render("Hello APNS!")
                  ).post("gcm",
                     ctx -> ctx.render("Hello GCM!")
                  )
            )
      );
   }
}
