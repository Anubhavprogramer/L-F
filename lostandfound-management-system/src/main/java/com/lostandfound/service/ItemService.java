package com.lostandfound.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.lostandfound.dto.ItemRequest;
import com.lostandfound.dto.ItemResponse;
import com.lostandfound.dto.UpdateItemStatusRequest;
import com.lostandfound.model.Item;
import com.lostandfound.model.Status;
import com.lostandfound.model.User;
import com.lostandfound.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {
	
	private final ItemRepository repository;
	
	public ItemService(ItemRepository repository) {
		this.repository = repository;
	}
	
	public ItemResponse create(ItemRequest request, User user) {  
		Item item = new Item();
		item.setName(request.getName());
		item.setDescription(request.getDescription());
		item.setLocation(request.getLocation());
		item.setStatus(Status.LOST);
		item.setDate(LocalDate.now());
		item.setReportedBy(user);
		
		Item saved = repository.save(item);
		return mapToResponse(saved);
	}
	
	public List<ItemResponse> getAll(){
		return repository.findAll()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}
	
	private ItemResponse mapToResponse(Item item) {
		ItemResponse res = new ItemResponse();
        res.setId(item.getId());
        res.setName(item.getName());
        res.setDescription(item.getDescription());
        res.setLocation(item.getLocation());
        res.setStatus(item.getStatus());
        res.setDate(item.getDate());
        return res;
	}
	
	public ItemResponse updateStatus(Long itemId, UpdateItemStatusRequest request, User user) {

	    Item item = repository.findById(itemId)
	            .orElseThrow(() -> new RuntimeException("Item not found"));

	    if (!item.getReportedBy().getId().equals(user.getId())) {
	        throw new RuntimeException("You are not allowed to update this item");
	    }
	    
	    if (request.getStatus() == Status.DELIVERED) {

	        if (request.getReceiverName() == null || request.getReceiverName().isBlank()) {
	            throw new RuntimeException("Receiver name is required for DELIVERED status");
	        }

	        if (request.getReceiverEmail() == null || request.getReceiverEmail().isBlank()) {
	            throw new RuntimeException("Receiver email is required for DELIVERED status");
	        }

	        item.setReceiverName(request.getReceiverName());
	        item.setReceiverEmail(request.getReceiverEmail());
	    }

	    item.setStatus(request.getStatus());

	    Item updated = repository.save(item);
	    return mapToResponse(updated);
	}
	
}
