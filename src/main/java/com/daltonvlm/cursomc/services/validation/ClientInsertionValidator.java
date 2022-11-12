package com.daltonvlm.cursomc.services.validation;

import com.daltonvlm.cursomc.domain.enums.ClientType;
import com.daltonvlm.cursomc.dto.ClientNewDTO;
import com.daltonvlm.cursomc.resources.exceptions.FieldMessage;
import com.daltonvlm.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertionValidator implements ConstraintValidator<ClientInsertion, ClientNewDTO> {
    @Override
    public void initialize(ClientInsertion constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(ClientType.NATURAL_PERSON.getCode()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
        }

        if (objDto.getType().equals(ClientType.LEGAL_PERSON.getCode()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
