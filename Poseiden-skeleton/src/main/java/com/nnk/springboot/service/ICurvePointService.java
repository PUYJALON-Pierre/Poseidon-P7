package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

public interface ICurvePointService {

  
  
  
  
  
  
  public List<CurvePoint> getCurvePoints() ;
  
  public CurvePoint getCurvePointById(int id) ;
  
  public CurvePoint saveCurvePoint(CurvePoint curvePoint) throws Exception ;
  
  public CurvePoint updateCurvePoint(CurvePoint curvePoint) throws Exception ;
  
  public void deleteCurvePoint(CurvePoint curvePoint) throws Exception ;
  
  
  
  
}
