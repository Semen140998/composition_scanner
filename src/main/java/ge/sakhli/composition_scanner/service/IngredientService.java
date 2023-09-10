package ge.sakhli.composition_scanner.service;

import ge.sakhli.composition_scanner.entity.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IngredientService {

    List<Ingredient> parseComposition(MultipartFile multipartFile);

}
