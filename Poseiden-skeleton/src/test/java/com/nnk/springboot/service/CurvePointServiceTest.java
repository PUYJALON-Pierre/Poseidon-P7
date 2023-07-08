package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@ExtendWith(SpringExtension.class) @SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class CurvePointServiceTest {

  @Autowired
  ICurvePointService iCurvePointService;

  @MockBean
  CurvePointRepository curvePointRepository;

  CurvePoint curvePoint = new CurvePoint();

  List<CurvePoint> listCurvePoints = new ArrayList<>();

  @BeforeEach
  public void setup() {

    curvePoint.setCurveId(12);
    curvePoint.setTerm(14.0);
    curvePoint.setValue(10.0);
    curvePoint.setId(1);
  }

  @Test
  public void getAllCurvePointsTest() {

    listCurvePoints.add(curvePoint);

    // when
    when(curvePointRepository.findAll()).thenReturn(listCurvePoints);

    assertEquals(iCurvePointService.getCurvePoints().size(), 1);

    assertEquals((int) iCurvePointService.getCurvePoints().get(0).getCurveId(), 12);
    assertEquals((double) iCurvePointService.getCurvePoints().get(0).getTerm(), 14.0);
    assertEquals((double) iCurvePointService.getCurvePoints().get(0).getValue(), 10.0);

  }

  @Test
  public void getCurvePointByIdTest() throws Exception {

    // when
    when(curvePointRepository.findById(curvePoint.getId())).thenReturn(Optional.of(curvePoint));
    
    
    assertEquals((int)iCurvePointService.getCurvePointById(curvePoint.getId()).getId(),
        1);
    assertEquals((int)iCurvePointService.getCurvePointById(curvePoint.getId()).getCurveId(),
        12);
    assertEquals((double)iCurvePointService.getCurvePointById(curvePoint.getId()).getTerm(),
        14.0);
    assertEquals((double)iCurvePointService.getCurvePointById(curvePoint.getId()).getValue(),
        10.0);

  }

  @Test
  public void saveCurvePointTest() throws Exception {

    CurvePoint curvePoint2 = new CurvePoint();

    curvePoint2.setCurveId(15);
    curvePoint2.setTerm(1.0);
    curvePoint2.setValue(15.0);
    curvePoint2.setId(2);

    when(curvePointRepository.save(curvePoint2)).thenReturn(curvePoint2);

    // when
    iCurvePointService.saveCurvePoint(curvePoint2);

    assertEquals(iCurvePointService.saveCurvePoint(curvePoint2), curvePoint2);

  }

  @Test
  public void updateCurvePointTest() throws Exception {

    curvePoint.setCurveId(9);
    curvePoint.setTerm(11.0);
    curvePoint.setValue(14.0);

    when(curvePointRepository.findById(curvePoint.getId())).thenReturn(Optional.of(curvePoint));
    when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
    // when

    assertEquals(iCurvePointService.updateCurvePoint(curvePoint), curvePoint);

    verify(curvePointRepository, times(1)).save(curvePoint);

  }

  @Test
  public void updateCurvePointFailTest() throws Exception {
    try {

      curvePoint.setCurveId(9);
      curvePoint.setTerm(11.0);
      curvePoint.setValue(14.0);

      when(curvePointRepository.findById(curvePoint.getId())).thenReturn(Optional.empty());

      // when
      iCurvePointService.updateCurvePoint(curvePoint);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "CurvePoint to update not founded");
    }
  }

  @Test
    public void deleteCurvePointTest() throws Exception {
    
      when(curvePointRepository.findById(curvePoint.getId())).thenReturn(Optional.of(curvePoint));
      
      // when
      iCurvePointService.deleteCurvePoint(curvePoint);
      
      verify(curvePointRepository, times(1)).delete(curvePoint);

    }

  @Test
  public void deleteCurvePointFailTest() throws Exception {

    try {
      when(curvePointRepository.findById(curvePoint.getId())).thenReturn(Optional.empty());

      // when
      iCurvePointService.deleteCurvePoint(curvePoint);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "CurvePoint to delete not founded");
    }

  }

}
