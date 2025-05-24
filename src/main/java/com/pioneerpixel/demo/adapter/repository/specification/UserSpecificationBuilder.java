package com.pioneerpixel.demo.adapter.repository.specification;

import com.pioneerpixel.demo.domain.EmailData;
import com.pioneerpixel.demo.domain.PhoneData;
import com.pioneerpixel.demo.domain.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserSpecificationBuilder {

    private final List<Specification<User>> specs = new ArrayList<>();
    private final boolean skipNullValues;

    private UserSpecificationBuilder(boolean skipNullValues) {
        this.skipNullValues = skipNullValues;
    }

    public static UserSpecificationBuilder builder(boolean skipNullValues) {
        return new UserSpecificationBuilder(skipNullValues);
    }

    public UserSpecificationBuilder nameStartsWith(String name) {
        addSpecification(name, UserSpecification::nameStartsWith);
        return this;
    }

    public UserSpecificationBuilder dateOfBirthAfter(LocalDate dateOfBirth) {
        addSpecification(dateOfBirth, UserSpecification::dateOfBirthAfter);
        return this;
    }

    public UserSpecificationBuilder emailEquals(String email) {
        addSpecification(email, UserSpecification::emailEquals);
        return this;
    }

    public UserSpecificationBuilder phoneEquals(String phone) {
        addSpecification(phone, UserSpecification::phoneEquals);
        return this;
    }

    public Specification<User> build() {
        return Specification.allOf(specs);
    }

    private <T> void addSpecification(T param, Function<T, Specification<User>> specification) {
        if (skipNullValue(param)) {
            return;
        }
        specs.add(specification.apply(param));
    }

    private boolean skipNullValue(Object param) {
        return skipNullValues && param == null;
    }

    private static class UserSpecification {
        private static Specification<User> nameStartsWith(String name) {
            return (root, query, builder) ->
                builder.like(root.get("name"), name + "%");
        }

        private static Specification<User> dateOfBirthAfter(LocalDate dateOfBirth) {
            return (root, query, builder) ->
                builder.greaterThan(root.get("dateOfBirth"), dateOfBirth);
        }

        private static Specification<User> emailEquals(String email) {
            return (root, query, builder) -> {
                Join<EmailData, User> emails = root.join("emails");
                return builder.equal(emails.get("email"), email);
            };
        }

        private static Specification<User> phoneEquals(String phone) {
            return (root, query, builder) -> {
                Join<PhoneData, User> phones = root.join("phones");
                return builder.equal(phones.get("phone"), phone);
            };
        }
    }
}
