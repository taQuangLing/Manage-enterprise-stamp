package com.icheck.backend.controller;

import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.request.stamp_request.BlockQrRequest;
import com.icheck.backend.request.stamp_request.DeleteQrRequest;
import com.icheck.backend.request.stamp_request.CreateStampRequest;
import com.icheck.backend.response.BaseResponse;
import com.icheck.backend.response.stamp_response.StampResponses;
import com.icheck.backend.response.stamp_response.BlockQrResponse;
import com.icheck.backend.response.stamp_response.DeleteQrResponse;
import com.icheck.backend.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stamp")
public class StampController {

    @Autowired
    private StampService service;
    @PostMapping("")
    public ResponseEntity<BaseResponse<StampResponses>> generate(@RequestBody CreateStampRequest request){
        BaseResponse<StampResponses> rsp = new BaseResponse<>(ErrorMessage.SUCCESS, service.createStamp(request));
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<BaseResponse<BlockQrResponse>> blockQr(@RequestBody BlockQrRequest request){
        System.out.println(request);
        BaseResponse<BlockQrResponse> rsp = new BaseResponse<>(ErrorMessage.SUCCESS, service.block(request));
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }
    @DeleteMapping("")
    public ResponseEntity<BaseResponse<DeleteQrResponse>> deleteQr(@RequestBody DeleteQrRequest request){
        BaseResponse<DeleteQrResponse> rsp = new BaseResponse<>(ErrorMessage.SUCCESS, service.delete(request));
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<BaseResponse<StampResponses>> getAll(){
        BaseResponse<StampResponses> rsp = new BaseResponse<>(ErrorMessage.SUCCESS, service.getAll());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }
}
