package com.vinicius.transfer_service.infrastructure.utility.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vinicius.transfer_service.api.dto.response.TransferInvoiceCheckpointResponse;
import com.vinicius.transfer_service.api.dto.response.TransferInvoicePreparationResponse;
import com.vinicius.transfer_service.api.dto.response.TransferInvoiceQueryCompleteResponse;
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

                modelMapper.createTypeMap(TransferInvoice.class, TransferInvoiceQueryCompleteResponse.class)
                                .addMappings(mapping -> mapping.map(
                                                transferInvoice -> transferInvoice.getId().getInvoiceId(),
                                                (destination, value) -> destination.setInvoiceId((String) value)));

                modelMapper.createTypeMap(TransferInvoice.class, TransferInvoicePreparationResponse.class)
                                .addMappings(mapping -> mapping.map(
                                                transferInvoice -> transferInvoice.getId().getInvoiceId(),
                                                (destination, value) -> destination.setInvoiceId((String) value)));

                modelMapper.createTypeMap(TransferInvoice.class, TransferInvoiceCheckpointResponse.class)
                                .addMappings(mapping -> mapping.map(
                                                transferInvoice -> transferInvoice.getId().getInvoiceId(),
                                                (destination, value) -> destination.setInvoiceId((String) value)));
        }
}
