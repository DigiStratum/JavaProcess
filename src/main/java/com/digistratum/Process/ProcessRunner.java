package com.digistratum.Process;

import com.digistratum.Process.Exception.ProcessException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * This should be called from the application main() with an instance of the RestApi; we will
 * put the RestApi into its own thread and let it run while we monitor. This gives us the
 * ability to potentially spawn more threads and/or perform additional work as needed while the
 * RestApi service does its job.
 *
 * TODO Add support for executing one or more Callbacks during the mainLoop
 */
abstract public class ProcessRunner {
	protected List<RunnableProcess> runnables;


	/**
	 * Constructor
	 */
	public ProcessRunner() {
		runnables = new ArrayList<>();
	}

	/**
	 * Start a runnable running
	 *
	 * @param runnable JGRunnable implementation instance that we can start running
	 */
	public void start(RunnableProcess runnable) {
		if (null == runnable) {
			throw new ProcessException("Attempted to start running a NULL runner!");
		}

		// Start the runnable process in a new thread
		String uniqueThreadName = "RunnableProcess-" + runnables.size();
		Thread t = new Thread(runnable, uniqueThreadName);
		t.start();
	}

	/**
	 * Wait for all runnables to finish running
	 */
	public void mainLoop() {
		boolean somethingIsRunning;
		do {
			// Make sure that we have at least one runnable still running!
			somethingIsRunning = false;
			for (RunnableProcess runnable : runnables) {

				// If this runnable is still running
				if (runnable.isRunning()) {
					somethingIsRunning = true;
				}
				else {
					// TODO: Remove this runnable from the collection as it is done
				}
			}
			// 100 millisecond delay between loops ensures no more than 10 loops per second
			// TODO: Make this configurable!
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// Restore the interrupted status
				Thread.currentThread().interrupt();
			}
		} while (somethingIsRunning);
	}
}
