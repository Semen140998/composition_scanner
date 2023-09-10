package ge.sakhli.composition_scanner.controller;

import ge.sakhli.composition_scanner.entity.Ingredient;
import ge.sakhli.composition_scanner.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/parseComposition")
    @CrossOrigin(origins = "http://192.168.100.161:8079")
    public ResponseEntity<List<Ingredient>> parseComposition(HttpServletRequest request) {
        MultipartHttpServletRequest filesRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFiles = filesRequest.getMultiFileMap().values().stream().findAny().orElse(Collections.emptyList());
        List<Ingredient> ingredientsOfComposition;
        if (!multipartFiles.isEmpty()) {
            ingredientsOfComposition = ingredientService.parseComposition(multipartFiles.get(0));
        } else {
            ingredientsOfComposition = new ArrayList<>();
        }
        return ResponseEntity.ok(ingredientsOfComposition); // Создайте HTML-шаблон с формой добавления пользователя (например, add-user.html)
    }
}
