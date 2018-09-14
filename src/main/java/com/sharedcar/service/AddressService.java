package com.sharedcar.service;

import com.sharedcar.dao.AddressDao;
import com.sharedcar.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressDao addressDao;

    public List<Address> find(Integer id, String addressName) {
        return addressDao.find(id, addressName);
    }

    public int addAddress(Address address) {
        return addressDao.addAddress(address);
    }

    public int modifyAddress(Address address) {
        return addressDao.modifyAddress(address);
    }

    public int removeAddress(Integer id) {
        return addressDao.removeAddress(id);
    }
}
