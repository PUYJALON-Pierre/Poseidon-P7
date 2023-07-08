package com.nnk.springboot.domain;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jparams.verifier.tostring.ToStringVerifier;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradeTest {

  
  @Test
  public void tradeHashCodeTest() {

    Trade trade1 = new Trade("Trade Account", "Type", 10.0);
    Trade trade2 = new Trade("Trade Account", "Type", 10.0);
   
    assertNotSame( trade1,  trade2);
    assertEquals( trade1.hashCode(),  trade2.hashCode());
  }

  @Test
  public void tradeToStringTest() {

    ToStringVerifier.forClass(Trade.class).verify();
  }

  
  
}
