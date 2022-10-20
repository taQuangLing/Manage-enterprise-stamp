package com.icheck.backend.DAO;

import com.icheck.backend.converter.BaseConverter;
import com.icheck.backend.entity.Pack;
import com.icheck.backend.entity.Stamp;
import com.icheck.backend.entity.User;
import com.icheck.backend.entity.UserPackage;
import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.repositority.StampRepo;
import com.icheck.backend.response.stamp_response.StampResponse;
import com.icheck.backend.response.stamp_response.StampResponses;
import com.icheck.backend.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StampDao {
    @Autowired
    private UserPackageDao upDao;
    @Autowired
    private PackDao pDao;
    @Autowired
    private Utilities util;
    @Autowired
    private UserDao uDao;
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
    public List<Stamp> createStamp(Long id){
        UserPackage userPackage = upDao.getById(id);
        Pack pack = pDao.getById(userPackage.getPackageId());
        User user = uDao.getById(userPackage.getUserId());


        List<Stamp> result = new ArrayList<>();
        for (int i = 0; i < pack.getQuantity(); i++){
            Stamp stamp = new Stamp();
            String code =util.genCode(user.getId(), i);
            stamp.setCode(code);
            stamp.setStatus(0);
            stamp.setDeleted(0);
            stamp.setUserPackageId(id);
            result.add(stamp);
            try{
                repo.save(stamp);
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        return result;
    }
}
