package main.service;

import main.model.VariantEntity;
import main.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantService {
    private final VariantRepository variantRepository;

    @Autowired
    public VariantService(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    public List<VariantEntity> findVariants(String id_questionnaire){
        return variantRepository.findVariants(id_questionnaire);
    }
    public VariantEntity findVariant(String id){
        return variantRepository.findById(id).get();
    }

    public void saveAll(List<VariantEntity> variantEntities){
        variantRepository.saveAll(variantEntities);
    }
}