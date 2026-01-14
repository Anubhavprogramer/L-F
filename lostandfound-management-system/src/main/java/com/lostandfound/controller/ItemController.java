package com.lostandfound.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lostandfound.dto.ItemRequest;
import com.lostandfound.dto.ItemResponse;
import com.lostandfound.service.ItemService;
import com.lostandfound.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/items")
//@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<ItemResponse> create(@Valid @RequestBody ItemRequest request) {
        ItemResponse res = service.create(request);
        return ApiResponse.success("Item reporter successfully", res);
    }

    @GetMapping
    public ApiResponse<List<ItemResponse>> getAll() {
        return ApiResponse.success("Items fetched successfully", service.getAll());
    }
}