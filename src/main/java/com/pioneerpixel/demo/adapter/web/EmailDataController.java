package com.pioneerpixel.demo.adapter.web;

import com.pioneerpixel.demo.adapter.web.dto.CreateEmailRequest;
import com.pioneerpixel.demo.adapter.web.dto.UpdateEmailRequest;
import com.pioneerpixel.demo.business.usecase.CreateEmailUseCase;
import com.pioneerpixel.demo.business.usecase.DeleteEmailUseCase;
import com.pioneerpixel.demo.business.usecase.UpdateEmailUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/emails")
public class EmailDataController {

    private final CreateEmailUseCase createEmail;
    private final UpdateEmailUseCase updateEmail;
    private final DeleteEmailUseCase deleteEmail;

    @PostMapping
    public void create(@RequestBody @Valid CreateEmailRequest request) {
        createEmail.execute(request.getEmail());
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateEmailRequest request) {
        updateEmail.execute(id, request.getEmail());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteEmail.execute(id);
    }
}
