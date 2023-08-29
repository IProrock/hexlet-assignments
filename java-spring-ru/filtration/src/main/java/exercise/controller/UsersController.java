package exercise.controller;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    // BEGIN
    @PersistenceContext
    private final EntityManager entityManager;
    @GetMapping(path = "")
    public List<User> getFilteredUsers(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {

//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> rootUser = criteriaQuery.from(User.class);

        List<Specification<User>> specifications = new ArrayList<>();
        if (firstName != null) {
            specifications.add(new UserSpecification(new SearchCriteria<String>("firstName", firstName)));
        }
        if (lastName != null) {
            specifications.add(new UserSpecification(new SearchCriteria<String>("lastName", lastName)));
        }

//        UserSpecification resultSpec = null;
//        if (!specifications.isEmpty()) {
//            resultSpec = specifications.get(0);
//        }
//
//        if (specifications != null) {
//            for (int i = 1; i < specifications.size(); i++) {
//                resultSpec = (UserSpecification) resultSpec.and(specifications.get(i));
//            }
//        }

        Specification<User> resultSpec = specifications.stream()
                .reduce(null, (specificationR, specification) -> {
                    if (specificationR == null) {
                        return specification;
                    }
                    Specification<User> rs = specificationR.and(specification);
                    return rs;
                });



        if (specifications != null) {
            return this.userRepository.findAll(resultSpec);
        }

        return this.userRepository.findAll();

    }
    // END
}

