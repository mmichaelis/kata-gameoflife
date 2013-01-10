package mmichaelis.kata.gameoflife;

import org.hamcrest.Matcher;

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

}
