package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Address;
import com.daltonvlm.cursomc.domain.City;
import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.domain.enums.ClientType;
import com.daltonvlm.cursomc.domain.enums.Profile;
import com.daltonvlm.cursomc.dto.ClientDTO;
import com.daltonvlm.cursomc.dto.ClientNewDTO;
import com.daltonvlm.cursomc.repositories.AddressRepository;
import com.daltonvlm.cursomc.repositories.ClientRepository;
import com.daltonvlm.cursomc.security.UserSS;
import com.daltonvlm.cursomc.services.exceptions.AuthorizationException;
import com.daltonvlm.cursomc.services.exceptions.DataIntegrityException;
import com.daltonvlm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public Client find(Integer id) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }

        Optional<Client> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
    }

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        obj = repository.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        return obj;
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public Client update(Client obj) {
        Client newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It is not possible to remove a Client with associated orders");
        }
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Access denied");
        }

        Client client = repository.findByEmail(email);
        if (client == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + user.getId() + ", Type: " + Client.class.getName());
        }

        return client;
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO objDto) {
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
    }

    public Client fromDTO(ClientNewDTO objDto) {
        Client client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()), bCryptPasswordEncoder.encode(objDto.getPassword()));
        City city = new City(objDto.getCityId(), null, null);
        Address address = new Address(null, objDto.getPublicArea(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getZipCode(), client, city);

        client.getAddresses().add(address);
        client.getPhones().add(objDto.getPhone1());
        if (objDto.getPhone2() != null) {
            client.getPhones().add(objDto.getPhone2());
        }
        if (objDto.getPhone3() != null) {
            client.getPhones().add(objDto.getPhone3());
        }
        return client;
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Access denied");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = prefix + user.getId() + ".jpg";
        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
