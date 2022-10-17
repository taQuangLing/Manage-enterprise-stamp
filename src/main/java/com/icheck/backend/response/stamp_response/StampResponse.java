package com.icheck.backend.response.stamp_response;

import com.icheck.backend.entity.Stamp;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StampResponse {
    private List<Stamp> stamps;
    public StampResponse(){
        stamps = new ArrayList<>();
    }
}
