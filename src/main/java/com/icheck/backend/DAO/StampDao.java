package com.icheck.backend.DAO;

import com.icheck.backend.entity.Stamp;
import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.repositority.StampRepo;
import com.icheck.backend.response.stamp_response.StampResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StampDao {
    @Autowired
    private StampRepo repo;
    public Stamp getById(Long id) {
        Stamp stamp = repo.findById(id).orElse(null);
        if (stamp == null) throw new ApiException(ErrorMessage.STAMP_EXISTED);
        return stamp;
    }

    public boolean update(Stamp stamp) {
        try{
            repo.save(stamp);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean delete(Long item) {
        try{
            repo.delete(getById(item));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
