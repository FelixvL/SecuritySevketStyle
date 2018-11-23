package com.security.presentatie.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.presentatie.domain.Trein;

import java.util.Optional;


@Service
@Transactional
public class TreinService {
	
	@Autowired
	private TreinRepository treinRepository;

	public Trein save(Trein trein){
		return treinRepository.save(trein);
	}
	
	public Optional<Trein> findById(Long id) {
		return treinRepository.findById(id);
	}

	public Iterable <Trein> findAll(){
		return treinRepository.findAll();
	}
}
