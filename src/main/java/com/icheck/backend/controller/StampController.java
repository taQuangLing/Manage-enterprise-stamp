package com.icheck.backend.controller;

import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.request.stamp_request.BlockQrRequest;
import com.icheck.backend.request.stamp_request.DeleteQrRequest;
import com.icheck.backend.response.BaseResponse;
import com.icheck.backend.response.stamp_response.BlockQrResponse;
import com.icheck.backend.response.stamp_response.DeleteQrResponse;
import com.icheck.backend.response.stamp_response.StampResponse;
import com.icheck.backend.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class StampController {

    @Autowired
    private StampService service;
    @PostMapping("/{id}")
    public ResponseEntity<BaseResponse<StampResponse>> generate(@PathVariable Long id){
        BaseResponse<StampResponse> rsp = new BaseResponse<>(ErrorMessage.SUCCESS, service.generate(id));
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<BaseResponse<BlockQrResponse>> blockQr(@RequestBody BlockQrRequest request){
        BaseResponse<BlockQrResponse> rsp = new BaseResponse<>(ErrorMessage.SUCCESS, service.block(request));
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }
    @DeleteMapping("")
    public ResponseEntity<BaseResponse<DeleteQrResponse>> deleteQr(@RequestBody DeleteQrRequest request){
        BaseResponse<DeleteQrResponse> rsp = new BaseResponse<>(ErrorMessage.SUCCESS, service.delete(request));
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }
}
