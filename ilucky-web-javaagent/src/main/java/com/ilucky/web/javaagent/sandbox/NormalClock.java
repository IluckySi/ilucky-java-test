package com.ilucky.web.javaagent.sandbox;

public class NormalClock extends Clock {

  @Override
  void checkState() {
      return;
  }

  @Override
  void delay() throws InterruptedException {
      Thread.sleep(1000L);
  }

}