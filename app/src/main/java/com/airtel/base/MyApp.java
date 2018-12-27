package com.airtel.base;

import com.airtel.core.Service;

public class MyApp {

  public static void main(String args[]) {
    Service s = new Service("test service");
    System.out.print(s.getName());
  }
}
