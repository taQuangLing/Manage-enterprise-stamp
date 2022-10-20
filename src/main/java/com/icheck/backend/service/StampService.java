package com.icheck.backend.service;

import com.icheck.backend.DAO.StampDao;
import com.icheck.backend.converter.BaseConverter;
import com.icheck.backend.entity.Stamp;
import com.icheck.backend.repositority.StampRepo;
import com.icheck.backend.request.stamp_request.BlockQrRequest;
import com.icheck.backend.request.stamp_request.DeleteQrRequest;
import com.icheck.backend.request.stamp_request.CreateStampRequest;
import com.icheck.backend.response.stamp_response.StampResponses;
import com.icheck.backend.response.stamp_response.BlockQrResponse;
import com.icheck.backend.response.stamp_response.DeleteQrResponse;
import com.icheck.backend.response.stamp_response.StampResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StampService {
    @Autowired
    private StampRepo repo;
    @Autowired
    private BaseConverter converter;
    @Autowired
    private StampDao sDao;
    public StampResponses createStamp(CreateStampRequest request) {
        StampResponses rsp = new StampResponses();
        List<Stamp> stamps = new ArrayList<>();
        for (Long id:request.getListId()){
            stamps = sDao.createStamp(id);
            for (Stamp item : stamps){
                rsp.getStamps().add(converter.toResponse(item, StampResponse.class));
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

    public StampResponses getAll() {
        List<Stamp> stamps = repo.findAll();
        StampResponses rsp = new StampResponses();
        for (Stamp item:stamps){
            rsp.getStamps().add(converter.toResponse(item, StampResponse.class));
        }
        return rsp;
    }
}
