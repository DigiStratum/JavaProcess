package com.digistratum.Process;

public interface RunnableProcess extends Runnable {

	/**
	 * Determine from the outside whether we are running
	 *
	 * @return boolean true if we are running, else false
	 */
	boolean isRunning();

	/**
	 * Stop the process loop from running any longer
	 */
	void stop();
}
