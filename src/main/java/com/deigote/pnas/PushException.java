package com.deigote.pnas;

public class PushException extends Exception {
   public PushException(Message<?> message, Throwable cause) {
      super("Error sending ".concat(message.toString()), cause);
   }
}
