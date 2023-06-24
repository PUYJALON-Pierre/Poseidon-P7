package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;


@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class BidListServiceTest {

  @Autowired
  IBidListService iBidListService;

  @MockBean
  BidListRepository bidListRepository;

  BidList bidList = new BidList();

  @BeforeEach
  public void setup() {

    bidList.setAccount("Account Test");
    bidList.setType("Type Test");
    bidList.setBidQuantity(10d);
    
    bidListRepository.save(bidList);
  }

  @AfterEach
  public void clear() {
    bidListRepository.deleteAll();
  }

  @Test
  public void getAllBidListsTest() {

    // when
    assertEquals(iBidListService.getAllBidLists().size(), 1);
    assertEquals(iBidListService.getAllBidLists().get(0).getAccount(), "Account Test");
    assertEquals(iBidListService.getAllBidLists().get(0).getType(), "Type Test");

  }

  @Test
  public void getBidListByBidListIdTest() throws Exception {

    
    
    // when
    assertEquals(iBidListService.getBidListByBidListId(bidList.getBidListId()).getAccount(), "Account Test");
    assertEquals(iBidListService.getBidListByBidListId(bidList.getBidListId()).getAccount(), "Type Test");

  }

  
  @Test
  public void saveBidListTest() throws Exception {
    BidList bidList2 = new BidList();

    bidList2.setAccount("Account Test2");
    bidList2.setType("Type Test2");
    bidList2.setBidQuantity(10d);

    // when
    iBidListService.saveBidList(bidList2);

    assertEquals(iBidListService.getAllBidLists().size(), 2);
    assertEquals(iBidListService.getAllBidLists().get(0).getAccount(), "Account Test2");
    assertEquals(iBidListService.getAllBidLists().get(0).getType(), "Type Test2");

  }

  @Test
  public void saveBidListTestFail() throws Exception {
    try {

      // when
      iBidListService.saveBidList(bidList);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "This bidList already exist");
      assertEquals(iBidListService.getAllBidLists().size(), 1);
    }
  }

  @Test
  public void updateBidListTest() throws Exception {

    bidList.setAccount("Account Test2");
    bidList.setType("Type Test2");
    bidList.setBidQuantity(11d);

    // When
    iBidListService.saveBidList(bidList);
    assertEquals(iBidListService.getAllBidLists().size(), 1);
    assertEquals(iBidListService.getAllBidLists().get(0).getAccount(), "Account Test2");
    assertEquals(iBidListService.getAllBidLists().get(0).getType(), "Type Test2");

  }

  @Test
      public void updateBidListFailTest() throws Exception {
        try {
         
          BidList bidListUpdate = new BidList();
          bidListUpdate.setAccount("Account Test2");
          bidListUpdate.setType("Type Test2");
          bidListUpdate.setBidQuantity(11d);
     
          //when 
          iBidListService.saveBidList(bidListUpdate);

        } catch (Exception e) {
          assertEquals(e.getMessage(), "BidList to update not founded");
          assertEquals (iBidListService.getAllBidLists().size(), 1);
          assertEquals (iBidListService.getAllBidLists().get(0).getAccount() , "Account Test");
          assertEquals (iBidListService.getAllBidLists().get(0).getType() , "Type Test");
        }
        }

  @Test
  public void deleteBidListTest() throws Exception {

      // when
      iBidListService.deleteBidList(bidList);

      assertEquals(iBidListService.getAllBidLists().size(), 0);

  }

  @Test
  public void deleteBidListFailTest() throws Exception {
    try {

      BidList bidListUpdate = new BidList();
      bidListUpdate.setAccount("Account Test2");
      bidListUpdate.setType("Type Test2");
      bidListUpdate.setBidQuantity(11d);

      // when
      iBidListService.deleteBidList(bidListUpdate);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "BidList to update not founded");
      assertEquals(iBidListService.getAllBidLists().size(), 1);
      assertEquals(iBidListService.getAllBidLists().get(0).getAccount(), "Account Test");
      assertEquals(iBidListService.getAllBidLists().get(0).getType(), "Type Test");
    }

  }

}
