package com.deigote.pnas.apns;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Credentials {

   public Credentials(String certFile, String password) {
      this.certFile = certFile;
      this.password = password;
   }

   final String certFile, password;

   protected InputStream getCertificate() {
      return Base64.getDecoder().wrap(
         new ByteArrayInputStream(certFile.getBytes(StandardCharsets.UTF_8))
      );
   }

}
