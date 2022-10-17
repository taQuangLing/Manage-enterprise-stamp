package com.icheck.backend.service;

import com.icheck.backend.DAO.PackDao;
import com.icheck.backend.DAO.StampDao;
import com.icheck.backend.DAO.UserDao;
import com.icheck.backend.DAO.UserPackageDao;
import com.icheck.backend.entity.Pack;
import com.icheck.backend.entity.Stamp;
import com.icheck.backend.entity.User;
import com.icheck.backend.entity.UserPackage;
import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.repositority.PackRepo;
import com.icheck.backend.repositority.StampRepo;
import com.icheck.backend.repositority.UserPackageRepo;
import com.icheck.backend.request.stamp_request.BlockQrRequest;
import com.icheck.backend.request.stamp_request.DeleteQrRequest;
import com.icheck.backend.response.stamp_response.BlockQrResponse;
import com.icheck.backend.response.stamp_response.DeleteQrResponse;
import com.icheck.backend.response.stamp_response.StampResponse;
import com.icheck.backend.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StampService {
    @Autowired
    private StampRepo repo;
    @Autowired
    private UserPackageDao upDao;
    @Autowired
    private PackDao pDao;
    @Autowired
    private Utilities util;
    @Autowired
    private UserDao uDao;
    @Autowired
    private StampDao sDao;
    public StampResponse generate(Long id) {
        UserPackage userPackage = upDao.getById(id);
        Pack pack = pDao.getById(userPackage.getPackageId());
        User user = uDao.getById(userPackage.getUserId());

        Stamp stamp = new Stamp();
        StampResponse rsp = new StampResponse();
        for (int i = 0; i < pack.getQuantity(); i++){
            String code =util.genCode(user.getId(), i);
            stamp.setCode(code);
            stamp.setStatus(0);
            stamp.setDeleted(0);
            stamp.setUserPackageId(id);
            rsp.getStamps().add(stamp);
            try{
                repo.save(stamp);
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        return rsp;

    }

    public BlockQrResponse block(BlockQrRequest request) {
        Stamp stamp;
        int numError = 0;
        for (Long item : request.getListId()) {
            stamp = sDao.getById(item);
            stamp.setStatus(2);
            if (!sDao.update(stamp)) numError++;
        }
        return new BlockQrResponse(numError, request.getListId().size() - numError);
    }

    public DeleteQrResponse delete(DeleteQrRequest request) {
        int numError = 0;
        for (Long item : request.getListId()){
            if (!sDao.delete(item))numError++;
        }
        return new DeleteQrResponse(numError, request.getListId().size() - numError);
    }
}
