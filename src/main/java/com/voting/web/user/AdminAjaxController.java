package com.voting.web.user;


import com.voting.model.User;
import com.voting.to.UserTo;
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
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public ResponseEntity<String>  createOrUpdate(@Valid UserTo userTo, BindingResult result) {
        if (result.hasErrors()) {
            String errStrings = createErrorStrings(result);
            return new ResponseEntity<>(errStrings, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (userTo.isNew()) {
            super.create(UserUtil.createNewFromTo(userTo));
        } else {
            super.update(userTo, userTo.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
