package mmichaelis.kata.gameoflife.evolution;

import mmichaelis.kata.gameoflife.cell.Cell;
import org.hamcrest.Matcher;

import javax.annotation.PostConstruct;

/**
 * @since 1.0
 */
public class EvolutionConfiguration {
  private Integer minimumLivingNeighbors;
  private Integer maximumLivingNeighbors;
  private Integer minimumLivingNeighborsForBirth;
  private UnderPopulationMatcher underPopulationMatcher;
  private OverCrowdedMatcher overCrowdedMatcher;
  private GiveBirthMatcher giveBirthMatcher;

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

  @PostConstruct
  public void init() {
    underPopulationMatcher = new UnderPopulationMatcher(minimumLivingNeighbors);
    overCrowdedMatcher = new OverCrowdedMatcher(maximumLivingNeighbors);
    giveBirthMatcher = new GiveBirthMatcher(minimumLivingNeighborsForBirth, maximumLivingNeighbors);
  }

  public Matcher<Cell> underPopulated() {
    return underPopulationMatcher;
  }

  public Matcher<Cell> overCrowded() {
    return overCrowdedMatcher;
  }

  public Matcher<Cell> givingBirth() {
    return giveBirthMatcher;
  }

  public void setMinimumLivingNeighborsForBirth(final Integer minimumLivingNeighborsForBirth) {
    this.minimumLivingNeighborsForBirth = minimumLivingNeighborsForBirth;
  }

  public Integer getMinimumLivingNeighborsForBirth() {
    return minimumLivingNeighborsForBirth;
  }

}
