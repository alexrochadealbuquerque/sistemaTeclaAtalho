package com.sistemaTeclaAtalho.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaTeclaAtalho.domain.Categoria;
import com.sistemaTeclaAtalho.dto.CategoriaDTO;
import com.sistemaTeclaAtalho.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria fromDTO(CategoriaDTO objDto) {

		return new Categoria(objDto.getId(), objDto.getNome(), "S");

	}

	public Categoria insert(Categoria obj) {

		obj.setId(null);
		obj.setAtivo("S");
		return repository.save(obj);
	}

	public List<Categoria> selectAll() {

		return repository.findAll();
	}

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null));
	}

	public Categoria update(Categoria obj) {

		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);

	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

	public void delete(Integer id) throws Exception {
		find(id);

		try {
			repository.deleteById(id);

		} catch (Exception e) {
			throw new Exception("Não é possível excluir uma categoria que possui produtos");
		}
	}
}
