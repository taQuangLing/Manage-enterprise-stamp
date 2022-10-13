package com.icheck.backend.repositority.impl;

import com.icheck.backend.converter.BaseConverter;
import com.icheck.backend.entity.Pack;
import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.response.pack_response.PackResponse;
import com.icheck.backend.response.pack_response.PacksResponse;
import com.icheck.backend.repositority.PackRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component("packRepoCustom")
public class PackRepoCustomImpl implements PackRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BaseConverter converter;
    @Override
    public PacksResponse search(String code, String name, int status) {
        PacksResponse packsResponse = new PacksResponse();
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        sql.append("select * from pack p where p.code LIKE :code and p.name LIKE :name ");
        params.put("code", "%"+code+"%");
        params.put("name", "%"+name+"%");

        if (status != -1){
            sql.append("and status = :status");
            params.put("status", status);
        }
        Query query = entityManager.createNativeQuery(sql.toString(), Pack.class);
        params.forEach(query::setParameter);
        List<Pack> packList =  query.getResultList();
        if (packList.isEmpty()){
            throw new ApiException(ErrorMessage.DATA_NOTFOUND);
        }
        for (Pack pack:packList) {
           packsResponse.getPackList().add(converter.toResponse(pack, PackResponse.class));
        }
        return packsResponse;
    }
}
