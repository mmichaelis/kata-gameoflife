package mmichaelis.kata.gameoflife.evolution;

import com.google.common.base.Predicate;
import org.hamcrest.Matcher;

/**
 * Predicates (Guava) and Matcher (Hamcrest) are nearly the same. If you want to use both
 * you have to define some mapping like in here.
 *
 * @since 1.0
 */
public class MatcherPredicate<T> implements Predicate<T> {
  private final Matcher<T> matcher;

  public MatcherPredicate(final Matcher<T> matcher) {
    this.matcher = matcher;
  }

  @Override
  public boolean apply(final T input) {
    return matcher.matches(input);
  }

  public static <T> Predicate<T> toPredicate(final Matcher<T> matcher) {
    return new MatcherPredicate<T>(matcher);
  }
}
