package mmichaelis.kata.gameoflife;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static mmichaelis.kata.gameoflife.CellIsAlive.cellIsAlive;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

/**
 * @since 1.0
 */
public class EvolutionConfiguration {
  private Integer minimumLivingNeighbors;
  private Integer maximumLivingNeighbors;
  private Integer minimumLivingNeighborsForBirth;

  public void setMinimumLivingNeighbors(final Integer minimumLivingNeighbors) {
    this.minimumLivingNeighbors = minimumLivingNeighbors;
  }

  public Integer getMinimumLivingNeighbors() {
    return minimumLivingNeighbors;
  }

  public void setMaximumLivingNeighbors(final Integer maximumLivingNeighbors) {
    this.maximumLivingNeighbors = maximumLivingNeighbors;
  }

  public Integer getMaximumLivingNeighbors() {
    return maximumLivingNeighbors;
  }

  public Matcher<Cell> underPopulated() {
    return new UnderPopulationMatcher(minimumLivingNeighbors);
  }

  public Matcher<Cell> overCrowded() {
    return new OverCrowdedMatcher(maximumLivingNeighbors);
  }

  public Matcher<Cell> givingBirth() {
    return new GiveBirthMatcher(minimumLivingNeighborsForBirth, maximumLivingNeighbors);
  }

  public void setMinimumLivingNeighborsForBirth(final Integer minimumLivingNeighborsForBirth) {
    this.minimumLivingNeighborsForBirth = minimumLivingNeighborsForBirth;
  }

  public Integer getMinimumLivingNeighborsForBirth() {
    return minimumLivingNeighborsForBirth;
  }

  private static class UnderPopulationMatcher extends CustomTypeSafeMatcher<Cell> {
    private final Integer minimum;

    private UnderPopulationMatcher(final Integer minimum) {
      super(String.format("under-populated"));
      this.minimum = minimum;
    }

    @Override
    protected boolean matchesSafely(final Cell item) {
      return lessThan(minimum).matches(item.countLinks(cellIsAlive()));
    }
  }

  private static class GiveBirthMatcher extends CustomTypeSafeMatcher<Cell> {
    private final Integer minimum;
    private final Integer maximum;

    private GiveBirthMatcher(final Integer minimum, final Integer maximum) {
      super(String.format("giving birth"));
      this.minimum = minimum;
      this.maximum = maximum;
    }

    @Override
    protected boolean matchesSafely(final Cell item) {
      return both(Matchers.greaterThanOrEqualTo(minimum))
              .and(Matchers.lessThanOrEqualTo(maximum))
              .matches(item.countLinks(cellIsAlive()));
    }
  }

  private static class OverCrowdedMatcher extends CustomTypeSafeMatcher<Cell> {
    private final Integer maximum;

    private OverCrowdedMatcher(final Integer maximum) {
      super(String.format("over-crowded"));
      this.maximum = maximum;
    }

    @Override
    protected boolean matchesSafely(final Cell item) {
      return greaterThan(maximum).matches(item.countLinks(cellIsAlive()));
    }
  }
}
