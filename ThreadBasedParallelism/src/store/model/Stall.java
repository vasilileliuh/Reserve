package store.model;

import store.services.StorePerformanceService;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Stall extends Thread {

    Logger log;
    List<Seller> sellers;

    volatile AtomicInteger servedBuyers = new AtomicInteger(0);
    StorePerformanceService performanceService = new StorePerformanceService();

    public Stall(Logger log, List<Seller> sellers) {
        this.log = log;
        this.sellers = sellers;
    }

    public void trade(Queue<Buyer> buyers) {
        servedBuyers.set(0);
        performanceService.startServeBuyers();
//            sellers.stream().forEach(seller -> {
//                seller.serveTheBuyer(buyers.poll());
//                servedBuyers.incrementAndGet();
//            });

        for (int i = 0; i < sellers.size(); i++) {
            Seller sell = sellers.get(i);
            Thread thread = new Thread(() -> {
                sell.serveTheBuyer(buyers.poll());
                synchronized (servedBuyers) {
                    servedBuyers.incrementAndGet();
                }
            });
            thread.start();

            if (i == sellers.size() - 1) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (servedBuyers) {
            log.info(performanceService.checkPerformance(servedBuyers.get()));
        }


    }
}
