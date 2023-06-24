package com.nnk.springboot.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.ICurvePointService;

@Service
public class CurvePointServiceImpl implements ICurvePointService {

  final static Logger logger = LogManager.getLogger(CurvePointServiceImpl.class);

  @Autowired
  CurvePointRepository curvePointRepository;

  @Override
  public List<CurvePoint> getCurvePoints() {
    logger.debug("Start finding all curvePoints");
    logger.info("Getting all curvePoints");

    if (curvePointRepository.findAll().isEmpty()) {
      logger.error("No curvePoints founded");
    }
    return curvePointRepository.findAll();
  }

  @Override
  public CurvePoint getCurvePointById(int id) {
    logger.debug("Finding curvePoint with id : {}", id);
    if (curvePointRepository.findById(id).isPresent()) {
      logger.info("Founded curvePoint with id : {}", id);
      return curvePointRepository.findById(id).get();
    } else {
      logger.error("No curvePoint founded with id : {}", id);
      return null;
    }
  }

  @Override
  public CurvePoint saveCurvePoint(CurvePoint curvePoint) throws Exception {

    logger.debug("Creating curvePoint with id : {}", curvePoint.getId());
    // Checking if curvePoint already exist
    if (curvePoint.getId() != null) {
      if (curvePointRepository.findById(curvePoint.getId()).isPresent()) {
        logger.error("This curvePoint cannot be created because already exist");
        throw new Exception("This curvePoint already exist");
      }
    } else {
      curvePointRepository.save(curvePoint);
      logger.info("CurvePoint with id : {} created", curvePoint.getId());
    }
    return curvePoint;
  }

  @Override
  public CurvePoint updateCurvePoint(CurvePoint curvePoint) throws Exception {

    logger.debug("Updating curvePoint with id : {}", curvePoint.getId());
    // Checking if curvePoint already exist
    if (curvePointRepository.findById(curvePoint.getId()).isPresent()) {
      logger.info("CurvePoint with id : {} updated", curvePoint.getId());
      return curvePointRepository.save(curvePoint);
    }

    else {
      logger.error("This curvePoint cannot be updated because not founded");
      throw new Exception("curvePoint to update not founded");
    }
  }

  @Override
  public void deleteCurvePoint(CurvePoint curvePoint) throws Exception {

    logger.debug("Deleting curvePoint with id : {}", curvePoint.getId());

    if (getCurvePointById(curvePoint.getId()) != null) {
      logger.info("CurvePoint with id : {} deleted", curvePoint.getId());
      curvePointRepository.delete(curvePoint);
    } else {
      logger.error("This curvePoint cannot be deleted because not founded");
      throw new Exception("curvePoint to delete not founded");
    }

  }

}
