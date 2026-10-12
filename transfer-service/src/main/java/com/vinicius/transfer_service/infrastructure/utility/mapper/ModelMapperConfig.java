package com.vinicius.transfer_service.infrastructure.utility.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vinicius.transfer_service.api.dto.request.TransferInvoicePreparationRequest;
import com.vinicius.transfer_service.api.dto.response.TransferInvoicePreparationResponse;
import com.vinicius.transfer_service.application.utility.Mapper;
import com.vinicius.transfer_service.domain.model.TransferInvoice;

@Configuration
public class ModelMapperConfig {
    @Bean
    Mapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        configuration(modelMapper);
        return modelMapper::map;
    }

    private void configuration(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setSourceNamingConvention(NamingConventions.NONE)
                .setDestinationNamingConvention(NamingConventions.NONE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(TransferInvoice.class, TransferInvoicePreparationResponse.class)
                .addMappings(mapping ->
                        mapping.map(transferInvoice -> transferInvoice.getId().getInvoiceId(), TransferInvoicePreparationResponse::setInvoiceId));

    }
}
