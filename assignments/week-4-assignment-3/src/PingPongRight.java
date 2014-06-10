// Import the necessary Java synchronization and scheduling classes.

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @class PingPongRight
 *
 * @brief This class implements a Java program that creates two
 *        instances of the PlayPingPongThread and start these thread
 *        instances to correctly alternate printing "Ping" and "Pong",
 *        respectively, on the console display.
 */
public class PingPongRight {
    /**
     * Number of iterations to run the test program.
     */
    public static int mMaxIterations = 10;
    
    /**
     * Latch that will be decremented each time a thread exits.
     */
    public static CountDownLatch latch = new CountDownLatch(2); // TODO - You fill in here

    /**
     * @class PlayPingPongThread
     *
     * @brief This class implements the ping/pong processing algorithm
     *         using the SimpleSemaphore to alternate printing "ping"
     *         and "pong" to the console display.
     */
    public static class PlayPingPongThread extends Thread
    {
        /**
         * Constructor initializes the data member.
         */
    	
    	int FIRST_SEM = 0;
    	int SECOND_SEM = 1;
    	int maxIteration ;
    	
        public PlayPingPongThread (/* TODO - You fill in here */String _str, SimpleSemaphore semaOne, SimpleSemaphore semaTwo, int maxIt)
        {
            // TODO - You fill in here.
        	mStringToPrint = _str;
        	mSimpleSema[FIRST_SEM] = semaOne;
        	mSimpleSema[SECOND_SEM] = semaTwo;
        	maxIteration = maxIt;
        }

        /**
         * Main event loop that runs in a separate thread of control
         * and performs the ping/pong algorithm using the
         * SimpleSemaphores.
         */
        public void run () 
        {
            // TODO - You fill in here.
        	
        	for(int mLoop = 1; mLoop <= maxIteration; mLoop++){
        		acquire();
        		System.out.println(mStringToPrint+"("+mLoop+")");
        		release();
        		
        	}
        	latch.countDown();
        }

        private void acquire(){
        	mSimpleSema[FIRST_SEM].acquireUninterruptibly();
        }
        
        private void release(){
        	mSimpleSema[SECOND_SEM].release();
        }
        
        /**
         * String to print (either "ping!" or "pong"!) for each
         * iteration.
         */
        // TODO - You fill in here.
        protected String mStringToPrint;

        /**
         * The two SimpleSemaphores use to alternate pings and pongs.
         */
        // TODO - You fill in here.
        private SimpleSemaphore[] mSimpleSema = new SimpleSemaphore[2];
    }

    /**
     * The main() entry point method into PingPongRight program. 
     */
    public static void main(String[] args) {
        try {         
            // Create the ping and pong SimpleSemaphores that control
            // alternation between threads.

            // TODO - You fill in here.
        	SimpleSemaphore pingSem = new SimpleSemaphore(1, true);
        	SimpleSemaphore pongSem = new SimpleSemaphore(0, true);

            System.out.println("Ready...Set...Go!");

            // Create the ping and pong threads, passing in the string
            // to print and the appropriate SimpleSemaphores.
            PlayPingPongThread ping =
                new PlayPingPongThread(/* TODO - You fill in here */"Ping!", pingSem, pongSem, mMaxIterations);
            PlayPingPongThread pong =
                new PlayPingPongThread(/* TODO - You fill in here */"Pong!", pongSem, pingSem, mMaxIterations);
            
            // Initiate the ping and pong threads, which will call the
            // run() hook method.
            ping.start();
            pong.start();

            // Use barrier synchronization to wait for both threads to
            // finish.

            // TODO - replace replace the following line with a
            // CountDownLatch barrier synchronizer call that waits for
            // both threads to finish.
            latch.await();
//            throw new java.lang.InterruptedException();
            
        } 
        catch (java.lang.InterruptedException e)
            {}

        System.out.println("Done!");
    }
}
