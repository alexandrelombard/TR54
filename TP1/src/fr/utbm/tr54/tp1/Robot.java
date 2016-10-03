package fr.utbm.tr54.tp1;

import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;

/**
 * Robot class
 * @author Alexandre Lombard
 *
 */
public class Robot {
	
	private Pilot pilot;
	
	private SampleProvider distanceProvider;
	private SampleProvider colorProvider;
	
	/**
	 * Default constructor
	 */
	public Robot() {
		//
	}
	
	/**
	 * Constructor initializing robot components
	 * @param pilot the pilot
	 * @param distanceProvider the distance provider
	 * @param colorProvider the color provider
	 */
	public Robot(Pilot pilot, SampleProvider distanceProvider, SampleProvider colorProvider) {
		this.pilot = pilot;
		this.distanceProvider = distanceProvider;
		this.colorProvider = colorProvider;
	}
	
	/**
	 * Gets the distance from the distance provider
	 * @return the distance
	 * @throws IllegalStateException thrown if the distance provider has not been initialized
	 */
	public float getDistance() {
		if(this.distanceProvider == null) {
			throw new IllegalStateException("Error: trying to access distance, but distance provider is null");
		}
		
		final float[] sample = new float[this.distanceProvider.sampleSize()];
		
		this.distanceProvider.fetchSample(sample, 0);
		
		return sample[0];
	}
	
	/**
	 * Gets the distance from the distance provider with application of a mean filter
	 * @param the number of samples used for the mean filter
	 * @return the distance
	 * @throws IllegalStateException thrown if the distance provider has not been initialized
	 */
	public float getDistance(int n) {
		if(this.distanceProvider == null) {
			throw new IllegalStateException("Error: trying to access distance, but distance provider is null");
		}
		
		final MeanFilter meanFilter = new MeanFilter(distanceProvider, n);
		
		final float[] sample = new float[meanFilter.sampleSize()];
		
		meanFilter.fetchSample(sample, 0);
		
		return sample[0];
	}
	
	/**
	 * Gets the RGB colors from the color provider
	 * @return the RGB colors
	 */
	public float[] getColor() {
		if(this.colorProvider == null) {
			throw new IllegalStateException("Error: trying to access to color, but color provider is null");
		}
		
		if(this.colorProvider.sampleSize() != 3) {
			throw new IllegalStateException("Error: trying to use color sensor in non-RGB mode");
		}
		
		final float[] sample = new float[this.colorProvider.sampleSize()];
		
		this.colorProvider.fetchSample(sample, 0);
		
		return sample;
	}

	/**
	 * Gets the pilot
	 * @return
	 */
	public Pilot getPilot() {
		return pilot;
	}

	/**
	 * Sets the pilot
	 * @param pilot
	 */
	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	/**
	 * Gets the distance provider
	 * @return the distance provider
	 */
	public SampleProvider getDistanceProvider() {
		return distanceProvider;
	}

	/**
	 * Sets the distance provider
	 * @param distanceProvider the distance provider (distance mode)
	 */
	public void setDistanceProvider(SampleProvider distanceProvider) {
		this.distanceProvider = distanceProvider;
	}

	/**
	 * Gets the color provider
	 * @return the color provider
	 */
	public SampleProvider getColorProvider() {
		return colorProvider;
	}

	/**
	 * Sets the color provider
	 * @param colorProvider the color provider (RGB mode)
	 */
	public void setColorProvider(SampleProvider colorProvider) {
		this.colorProvider = colorProvider;
	}
}
