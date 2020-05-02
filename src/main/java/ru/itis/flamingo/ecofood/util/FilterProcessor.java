package ru.itis.flamingo.ecofood.util;

import com.google.common.collect.ImmutableList;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class FilterProcessor <F, E>{

    private final ImmutableList<Rule<F, E>> rules;

    private FilterProcessor(Collection<Rule<F, E>> rules) {
        this.rules = ImmutableList.copyOf(rules);
    }

    public static <F, E, T> FilterProcessor.Builder<F, E> strict(Function<F, T> getter, Function<T, Specification<E>> specBuilder) {
        return new Builder<F, E>().strict(getter, specBuilder);
    }

    public static class Builder<F, E> {
        private final List<Rule<F, E>> rules = new ArrayList<>();

        public Builder<F, E> relaxed(Function<F, ?> getter, Function<F, Specification<E>> specCreator) {
            rules.add(new RelaxedRule<>(getter, specCreator));
            return this;
        }

        public <T> Builder<F, E> strict(Function<F, T> getter, Function<T, Specification<E>> specBuilder) {
            rules.add(new StrictRule<>(getter, specBuilder));
            return this;
        }

        public FilterProcessor<F, E> build() {
            return new FilterProcessor<>(rules);
        }
    }

    public Specification<E> buildSpec(F filter) {
        Specification<E> spec = Specification.where(null);
        for (Rule<F, E> rule : rules) {
            if (rule.notEmpty(filter)) {
                spec = spec.and(rule.buildSpec(filter));
            }
        }
        return spec;
    }

    private interface Rule<F, E> {

        boolean notEmpty(F filter);

        Specification<E> buildSpec(F filter);
    }

    private static class RelaxedRule<F, E> implements Rule<F, E> {
        private final Function<F, ?> getter;
        private final Function<F, Specification<E>> specCreator;

        private RelaxedRule(Function<F, ?> getter, Function<F, Specification<E>> specCreator) {
            this.getter = getter;
            this.specCreator = specCreator;
        }

        @Override
        public boolean notEmpty(F filter) {
            return getter.apply(filter) != null;
        }

        @Override
        public Specification<E> buildSpec(F filter) {
            return specCreator.apply(filter);
        }
    }

    private static class StrictRule<F, E, T> implements Rule<F, E> {
        private final Function<F, T> getter;
        private final Function<T, Specification<E>> specBuilder;

        private StrictRule(Function<F, T> getter, Function<T, Specification<E>> specBuilder) {
            this.getter = getter;
            this.specBuilder = specBuilder;
        }

        @Override
        public boolean notEmpty(F filter) {
            return getter.apply(filter) != null;
        }

        @Override
        public Specification<E> buildSpec(F filter) {
            T value = getter.apply(filter);
            return specBuilder.apply(value);
        }
    }

}
