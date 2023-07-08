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
public class BidListTest {

  
  @Test
  public void bidListHashCodeTest() {

   BidList bid1 = new BidList("account", "type", 10d);
   BidList bid2 = new BidList("account", "type", 10d);
    assertNotSame(bid1, bid2);
    assertEquals(bid1.hashCode(), bid2.hashCode());
  }

  @Test
  public void bidListToStringTest() {

    ToStringVerifier.forClass(BidList.class).verify();
  }

  
  
}
