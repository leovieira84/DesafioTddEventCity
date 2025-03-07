package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {
	@Autowired
	private EventRepository repository;

	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event event = repository.getReferenceById(id);
			copyDtoToEntity(dto, event);
			return new EventDTO(repository.save(event)); 
			
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("id not found " + id);
		}
	}
	
	private void copyDtoToEntity(EventDTO dto, Event entity) {
		entity.setCity(new City(dto.getCityId(), ""));
		entity.setDate(dto.getDate());
		entity.setName(dto.getName());
		entity.setUrl(dto.getUrl());
	}
}
