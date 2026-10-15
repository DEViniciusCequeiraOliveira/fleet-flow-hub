package com.vinicius.shipment_service.infrastructure.utility.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vinicius.shipment_service.api.dto.response.InvoiceItemQueryCompleteResponse;
import com.vinicius.shipment_service.application.utility.Mapper;
import com.vinicius.shipment_service.domain.model.InvoiceItem;

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

        modelMapper.createTypeMap(
                InvoiceItem.class,
                InvoiceItemQueryCompleteResponse.class);

    }
}