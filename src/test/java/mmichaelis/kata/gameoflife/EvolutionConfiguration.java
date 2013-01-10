package mmichaelis.kata.gameoflife;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static mmichaelis.kata.gameoflife.CellIsAlive.cellIsAlive;

/**
 * @since 1.0
 */
public class EvolutionConfiguration {
  private Integer minimumLivingNeighbors;
  private Integer maximumLivingNeighbors;

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

  private static class UnderPopulationMatcher extends CustomTypeSafeMatcher<Cell> {
    private final Integer minimum;

    private UnderPopulationMatcher(final Integer minimum) {
      super(String.format("under-populated"));
      this.minimum = minimum;
    }

    @Override
    protected boolean matchesSafely(final Cell item) {
      return Matchers.lessThan(item.countLinks(cellIsAlive())).matches(minimum);
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
      return Matchers.greaterThan(item.countLinks(cellIsAlive())).matches(maximum);
    }
  }
}
