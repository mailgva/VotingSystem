package com.voting.web.dish;

import com.voting.model.Dish;
import com.voting.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.voting.util.Util.createErrorStrings;

@RestController
@RequestMapping("/ajax/dishes")
public class DishAjaxController extends AbstractDishController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable("id") Dish dish) {
        return dish;
    }


    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public ResponseEntity<String>  createOrUpdate(@Valid Dish dish, BindingResult result) {
        if (result.hasErrors()) {
            String errStrings = createErrorStrings(result);
            return new ResponseEntity<>(errStrings, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (dish.isNew()) {
            super.create(dish);
        } else {
            super.update(dish, dish.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
