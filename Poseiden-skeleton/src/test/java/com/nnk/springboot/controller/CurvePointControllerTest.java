package com.nnk.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.CurvePointController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.IBidListService;
import com.nnk.springboot.service.ICurvePointService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CurvePointController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CurvePointControllerTest {
  
  
  @Autowired
  MockMvc mockMvc;
  
  @MockBean
  ICurvePointService iCurvePointService;
  
  @MockBean
  CurvePointRepository curvePointRepository;
  
  private CurvePoint curvePoint;
  private List<CurvePoint> curvePoints = new ArrayList<>();
  
  @BeforeEach
  public void setup() {
    curvePoint = new CurvePoint(1, 5d, 10d);

    curvePoints.add(curvePoint);
    
    when(iCurvePointService.getCurvePoints()).thenReturn(curvePoints);
  }
  
  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewCurvePointPageModelTest() throws Exception {
    mockMvc.perform(get("/curvePoint/list")).andExpect(status().isOk());
  }
  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewAddCurvePointPageModelTest() throws Exception {
    mockMvc.perform(get("/curvePoint/add")).andExpect(status().isOk());
  }
  
  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void postCurvePointTest() throws Exception {
    mockMvc.perform(post("/curvePoint/validate")
        .with(csrf().asHeader())
        .param("curveId", "1")
        .param("term", "5d")
        .param("value", "10d"))
        .andExpect(redirectedUrl("/curvePoint/list")).andExpect(status().is3xxRedirection());

  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewUpdateCurvePointPageModelTest() throws Exception {
  
    when(curvePointRepository.findById(6)).thenReturn(Optional.of(curvePoint));
    when(iCurvePointService.getCurvePointById(6)).thenReturn(curvePoint);
    
    mockMvc.perform(get("/curvePoint/update/{id}", "6")).andExpect(status().isOk());}

  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void updateCurvePointTest() throws Exception {
 
    when(curvePointRepository.findById(6)).thenReturn(Optional.of(curvePoint));
    when(iCurvePointService.updateCurvePoint(curvePoint)).thenReturn(curvePoint);
    
    mockMvc.perform(post("/curvePoint/update/{id}", "6")
        .with(csrf().asHeader())
        .param("curveId", "1")
        .param("term", "5d")
        .param("value", "10d"))
        .andExpect(redirectedUrl("/curvePoint/list")).andExpect(status().is3xxRedirection());

  }
  
  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void deleteCurvePointTest() throws Exception {
    
    when(curvePointRepository.findById(6)).thenReturn(Optional.of(curvePoint));
    when(iCurvePointService.getCurvePointById(6)).thenReturn(curvePoint);
    
    mockMvc.perform(get("/curvePoint/delete/{id}", "6")
        .with(csrf().asHeader())
        .param("curveId", "1")
        .param("term", "5d")
        .param("value", "10d"))
        .andExpect(redirectedUrl("/curvePoint/list")).andExpect(status().is3xxRedirection());

  }
  

  
  
}
