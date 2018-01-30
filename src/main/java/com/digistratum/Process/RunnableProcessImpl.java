package com.digistratum.Process;

import org.apache.log4j.Logger;

public class RunnableProcessImpl implements RunnableProcess {
	// ref: http://www.java67.com/2015/07/how-to-stop-thread-in-java-example.html
	private volatile boolean amRunning = false;
	protected final static Logger log = Logger.getLogger(RunnableProcessImpl.class);

	@Override
	public boolean isRunning() {
		return amRunning;
	}

	@Override
	public void stop() {
		// Don't do the work of stopping if we are not running
		if (! amRunning) return;
		amRunning = false;
	}

	@Override
	public void run() {
		// Do-nothing run loop
		amRunning = true;
		while (amRunning) {
			try {
				// TODO: Add support for real work to execute here as a callable of some sort (Closure?)
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Restore the interrupted status
				Thread.currentThread().interrupt();
			}
		}
		stop();
	}
}
