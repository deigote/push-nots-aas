package com.deigote.pnas.apns;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Credentials {

   // Used by Jackson
   private Credentials() {
      this(null, null);
   }

   public Credentials(String certFile, String password) {
      this.certificate = certFile;
      this.password = password;
   }

   private final String certificate;
   protected final String password;

   protected InputStream getCertificate() {
      return new ByteArrayInputStream(Base64.getDecoder().decode(certificate));
   }

   @Override
   public String toString() {
      return "Credentials{" +
         "certFile='" + (certificate != null ? "xxx" : "null") + '\'' +
         ", password='" + (password != null ? "xxx" : "null") + '\'' +
         '}';
   }
}
