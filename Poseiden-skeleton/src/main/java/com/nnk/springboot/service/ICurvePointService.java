package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

/**
 * Interface for services operations concerning CurvePoint in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
public interface ICurvePointService {

  /**
   * Retrieve a list of all CurvePoint
   * 
   * @return List of CurvePoint
   */
  public List<CurvePoint> getCurvePoints();

  /**
   * Retrieve a CurvePoint by his id
   * 
   * @param id - int
   * @return CurvePoint
   * @throws Exception
   */
  public CurvePoint getCurvePointById(int id);

  /**
   * Save a CurvePoint
   * 
   * @param curvePoint - CurvePoint
   * @return CurvePoint
   * @throws Exception
   */
  public CurvePoint saveCurvePoint(CurvePoint curvePoint) throws Exception;

  /**
   * Update an existing CurvePoint
   * 
   * @param curvePoint - CurvePoint
   * @return CurvePoint
   * @throws Exception
   */
  public CurvePoint updateCurvePoint(CurvePoint curvePoint) throws Exception;

  /**
   * Delete a CurvePoint
   * 
   * @param curvePoint - CurvePoint
   * @throws Exception
   */
  public void deleteCurvePoint(CurvePoint curvePoint) throws Exception;

}
