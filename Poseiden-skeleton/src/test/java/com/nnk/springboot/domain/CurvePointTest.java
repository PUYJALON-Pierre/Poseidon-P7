package com.nnk.springboot.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jparams.verifier.tostring.ToStringVerifier;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CurvePointTest {

  CurvePoint curvePoint1 = new CurvePoint(10, 10d, 30d);
  CurvePoint curvePoint2 = new CurvePoint(10, 10d, 30d);
  
  @Test
  public void curvePointHashCodeTest() {
   
    assertNotSame( curvePoint1,  curvePoint2);
    assertEquals( curvePoint1.hashCode(),  curvePoint2.hashCode());
  }

  @Test
  public void curvePointToStringTest() {

    ToStringVerifier.forClass(CurvePoint.class).verify();
  }

  @Test
  public void testEqualsSameObj() {
    assertThat(curvePoint1).isEqualTo(curvePoint2);
    assertFalse(curvePoint1.equals(null));
  }
  
}
