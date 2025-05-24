package com.pioneerpixel.demo.adapter.web;

import com.pioneerpixel.demo.adapter.web.dto.CreatePhoneRequest;
import com.pioneerpixel.demo.adapter.web.dto.UpdatePhoneRequest;
import com.pioneerpixel.demo.business.usecase.CreatePhoneUseCase;
import com.pioneerpixel.demo.business.usecase.DeletePhoneUseCase;
import com.pioneerpixel.demo.business.usecase.UpdatePhoneUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/phones")
public class PhoneDataController {

    private final CreatePhoneUseCase createPhone;
    private final UpdatePhoneUseCase updatePhone;
    private final DeletePhoneUseCase deletePhone;

    @PostMapping
    public void create(@RequestBody @Valid CreatePhoneRequest request) {
        createPhone.execute(request.getPhone());
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdatePhoneRequest request) {
        updatePhone.execute(id, request.getPhone());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deletePhone.execute(id);
    }
}
