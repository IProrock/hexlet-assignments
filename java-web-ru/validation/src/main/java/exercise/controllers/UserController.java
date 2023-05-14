package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(fn -> ((String) fn).length() > 0, "First Name should not be empty");

        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(ln -> ((String) ln).length() > 0, "Last name should not be empty");

        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(em -> EmailValidator.getInstance().isValid(em), "Email is not valid");

        Validator<String> passValidator = ctx.formParamAsClass("password", String.class)
                .check(pw -> StringUtils.isNumeric(pw), "Pass should be with num only")
                .check(pw -> ((String) pw).length() > 3, "Length should be 4 at least");

        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        User user = new User(firstName, lastName, email, password);

        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                firstNameValidator,
                lastNameValidator,
                emailValidator,
                passValidator
        );

//        Map<String, String> errors = Map.of("a", "n");

        if (!errors.isEmpty()) {
            ctx.status(422);
            ctx.attribute("errors", errors);
            ctx.attribute("user", user);

            ctx.render("users/new.html");

//            ctx.redirect("/users/new", 422);

        } else {
            user.save();
            ctx.attribute("flash", "Пользователь успешно создан");

            ctx.redirect("/users", 302);
        }

        // END
    };
}
