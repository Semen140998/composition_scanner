package ge.sakhli.composition_scanner.service;

import ge.sakhli.composition_scanner.entity.Ingredient;
import ge.sakhli.composition_scanner.repository.IngredientRepository;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private Tesseract tesseract;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> parseComposition(MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            BufferedImage image = ImageIO.read(inputStream);
            if (image == null) {
                throw new Exception("Ошибка при парсинге файла!");
            }
            String parsedText = tesseract.doOCR(image);
            Set<Ingredient> allIngredients = new HashSet<>(ingredientRepository.findAll());
            List<Ingredient> foundIngredients = new ArrayList<>();
            for (Ingredient ingredient : allIngredients) {
                if (StringUtils.containsIgnoreCase(parsedText, ingredient.getIngredientName())) {
                    foundIngredients.add(ingredient);
                }
            }
            foundIngredients.sort((ingredient1, ingredient2) ->
                    Integer.compare(ingredient2.getIngredientClass(), ingredient1.getIngredientClass()));
            return foundIngredients;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
