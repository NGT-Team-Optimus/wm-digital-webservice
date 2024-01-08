package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Fund;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IFundService {
    public List<Fund> getAllAvailableFunds();
    public int getfundIdByFundName(String fundName);
}
