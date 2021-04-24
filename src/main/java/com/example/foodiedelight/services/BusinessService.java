package com.example.foodiedelight.services;

import com.example.foodiedelight.models.Business;
import com.example.foodiedelight.repositories.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {
  @Autowired
  BusinessRepository businessRepository;

  public Business createBusiness(Business newBusiness) {
    return businessRepository.save(newBusiness);
  }

  public List<Business> findBusinessesForOwner(int ownerId) {
    return businessRepository.findBusinessesForOwner(ownerId);
  }

  public List<Business> findAllBusinesses() {
    return (List<Business>) businessRepository.findAll();
  }

  public int updateBusiness(int bid, Business newBusiness) {
    Business oldBusiness = businessRepository.findBusinessById(bid);
    oldBusiness.setName(newBusiness.getName());
    oldBusiness.setType(newBusiness.getType());
    businessRepository.save(oldBusiness);
    return 1;
  }

  public int deleteBusiness(int bid) {
    businessRepository.deleteById(bid);
    return 1;
  }
}

