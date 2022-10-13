package com.icheck.backend.service;

import com.icheck.backend.DAO.PackDao;
import com.icheck.backend.converter.BaseConverter;
import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.repositority.PackRepo;
import com.icheck.backend.entity.Pack;
import com.icheck.backend.repositority.PackRepoCustom;
import com.icheck.backend.request.pack_request.AddPackRequest;
import com.icheck.backend.request.pack_request.UpdatePackRequest;
import com.icheck.backend.response.pack_response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PackService {
    @Autowired
    private PackRepo repo;
    @Autowired
    private PackDao dao;
    @Qualifier("packRepoCustom")
    @Autowired
    private PackRepoCustom repoCustom;

    @Autowired
    BaseConverter converter;


    public DeletePackResponse delete(Long id) {
        Pack pack = dao.getById(id);

        try {
            repo.delete(pack);
        }catch(Exception e){
            e.getStackTrace();
            return null;
        }
        return converter.toResponse(pack, DeletePackResponse.class);
    }

    public PackResponse getById(Long id) {

        return converter.toResponse(dao.getById(id), PackResponse.class);
    }


    public PacksResponse search(String code, String name, int status) {
        return repoCustom.search(code, name, status);
    }

    public AddPackResponse add(AddPackRequest request) {
        Pack pack = converter.toEntity(request, Pack.class);
        if (repo.findByCode(pack.getCode()) != null){
            // code da ton tai
            throw new ApiException(ErrorMessage.CODE_EXISTED);
        }
        pack.setStatus(1);
        try {
            repo.save(pack);
        }catch (Exception e){
            e.getStackTrace();
            return null;
        }
        return converter.toResponse(pack, AddPackResponse.class);
    }
    public UpdatePackResponse update(UpdatePackRequest request) {
        Pack packUpdated = converter.toEntity(request, Pack.class);
        Pack pack = dao.getById(request.getId());

        if (repo.findByCode(pack.getCode()) != null && pack.getCode() != packUpdated.getCode()){
            throw new ApiException(ErrorMessage.CODE_EXISTED);
        }
        try {
            repo.save(packUpdated);
        }catch (Exception e){
            e.getStackTrace();
            return null;
        }
        return converter.toResponse(packUpdated, UpdatePackResponse.class);
    }

}
