package com.lostandfound.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.lostandfound.dto.ItemRequest;
import com.lostandfound.dto.ItemResponse;
import com.lostandfound.model.Item;
import com.lostandfound.model.Status;
import com.lostandfound.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {
	
	private final ItemRepository repository;
	
	public ItemService(ItemRepository repository) {
		this.repository = repository;
	}
	
	public ItemResponse create(ItemRequest request) {
		Item item = new Item();
		item.setName(request.getName());
		item.setDescription(request.getDescription());
		item.setLocation(request.getLocation());
		item.setStatus(Status.LOST);
		item.setDate(LocalDate.now());
		
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
	
}
