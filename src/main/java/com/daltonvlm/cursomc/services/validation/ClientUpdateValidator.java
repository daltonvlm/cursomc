package com.daltonvlm.cursomc.services.validation;

import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.dto.ClientDTO;
import com.daltonvlm.cursomc.repositories.ClientRepository;
import com.daltonvlm.cursomc.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repository;

    @Override
    public void initialize(ClientUpdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Client obj = repository.findByEmail(objDto.getEmail());
        if (obj != null && !obj.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
