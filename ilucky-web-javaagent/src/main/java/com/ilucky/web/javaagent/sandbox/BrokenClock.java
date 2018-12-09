package com.ilucky.web.javaagent.sandbox;

/**
* 一个损坏的钟实现
*/
public class BrokenClock extends Clock {

 @Override
 void checkState() {
     throw new IllegalStateException();
 }

 @Override
 void delay() throws InterruptedException {
     Thread.sleep(5000L);
 }

}