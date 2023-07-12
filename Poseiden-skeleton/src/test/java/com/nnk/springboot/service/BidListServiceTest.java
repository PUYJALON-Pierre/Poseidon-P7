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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class BidListServiceTest {

  @Autowired
  IBidListService iBidListService;

  @MockBean
  BidListRepository bidListRepository;

  BidList bidList = new BidList();

  List<BidList> listBidlist = new ArrayList<>();

  @BeforeEach
  public void setup() {

    bidList.setAccount("Account Test");
    bidList.setType("Type Test");
    bidList.setBidQuantity(10d);
    bidList.setBidListId(1);

  }

  @Test
  public void getAllBidListsTest() {

    listBidlist.add(bidList);

    // when
    when(bidListRepository.findAll()).thenReturn(listBidlist);

    assertEquals(iBidListService.getAllBidLists().size(), 1);
    assertEquals(iBidListService.getAllBidLists().get(0).getAccount(), "Account Test");
    assertEquals(iBidListService.getAllBidLists().get(0).getType(), "Type Test");

  }

  @Test
  public void getBidListByBidListIdTest() throws Exception {

    // when
    when(bidListRepository.findById(bidList.getBidListId())).thenReturn(Optional.of(bidList));
    
    
    assertEquals(iBidListService.getBidListByBidListId(bidList.getBidListId()).getAccount(),
        "Account Test");
    assertEquals(iBidListService.getBidListByBidListId(bidList.getBidListId()).getType(),
        "Type Test");

  }

  @Test
  public void saveBidListTest() throws Exception {
    BidList bidList2 = new BidList();

    bidList2.setAccount("Account Test2");
    bidList2.setType("Type Test2");
    bidList2.setBidQuantity(10d);

    when(bidListRepository.save(bidList2)).thenReturn(bidList2);

    // when
    iBidListService.saveBidList(bidList2);

    assertEquals(iBidListService.saveBidList(bidList2), bidList2);

  }

  @Test
  public void updateBidListTest() throws Exception {

    bidList.setAccount("Account Test2");
    bidList.setType("Type Test2");
    bidList.setBidQuantity(11d);

    when(bidListRepository.findById(bidList.getBidListId())).thenReturn(Optional.of(bidList));
    when(bidListRepository.save(bidList)).thenReturn(bidList);
    // when

    assertEquals(iBidListService.updateBidList(bidList), bidList);

    verify(bidListRepository, times(1)).save(bidList);

  }

  @Test
  public void updateBidListFailTest() throws Exception {
    try {

      bidList.setAccount("Account Test2");
      bidList.setType("Type Test2");
      bidList.setBidQuantity(11d);

      when(bidListRepository.findById(bidList.getBidListId())).thenReturn(Optional.empty());

      // when
      iBidListService.updateBidList(bidList);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "BidList to update not founded");
    }
  }

  @Test
    public void deleteBidListTest() throws Exception {
    
      when(bidListRepository.findById(bidList.getBidListId())).thenReturn(Optional.of(bidList));
      
      // when
      iBidListService.deleteBidList(bidList);
      
      verify(bidListRepository, times(1)).delete(bidList);

    }

  @Test
  public void deleteBidListFailTest() throws Exception {

    try {
      when(bidListRepository.findById(bidList.getBidListId())).thenReturn(Optional.empty());

      // when
      iBidListService.deleteBidList(bidList);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "BidList cannot be founded with this id");
    }

  }

}
