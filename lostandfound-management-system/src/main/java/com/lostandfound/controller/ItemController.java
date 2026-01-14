package com.lostandfound.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lostandfound.dto.ItemRequest;
import com.lostandfound.dto.ItemResponse;
import com.lostandfound.dto.UpdateItemStatusRequest;
import com.lostandfound.model.User;
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
    public ApiResponse<ItemResponse> create(@Valid @RequestBody ItemRequest request, @AuthenticationPrincipal User user) {
        ItemResponse res = service.create(request, user);
        return ApiResponse.success("Item reporter successfully", res);
    }

    @GetMapping
    public ApiResponse<List<ItemResponse>> getAll() {
        return ApiResponse.success("Items fetched successfully", service.getAll());
    }
    
    @PutMapping("/{itemId}/status")
    public ApiResponse<ItemResponse> updateStatus(@PathVariable Long itemId, @Valid @RequestBody UpdateItemStatusRequest req, @AuthenticationPrincipal User user ) {
    	ItemResponse res = service.updateStatus(itemId, req, user);
    	return ApiResponse.success("Item status updated successfully", res);
    }
}