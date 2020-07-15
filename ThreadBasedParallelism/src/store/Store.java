package store;

import store.model.Buyer;
import store.model.Seller;
import store.model.Stall;
import store.services.BuyerService;
import store.services.LogService;
import store.services.SellersService;
import store.services.StorePerformanceService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class Store {

    final Logger LOGGER = new LogService().LOGGER;

    Queue<Buyer> buyers = new LinkedList<>();
    List<Seller> sellers = new ArrayList<>();
    Stall stall = new Stall(LOGGER, sellers);
    /**
     * Maximum buyer count
     */
    byte storeSize = 100;

    public void openStore() {
        SellersService.inviteSellers(sellers, 4 );
        BuyerService.inviteNewBuyers(buyers, storeSize);
        LOGGER.info("Store is open");
        startTrading();
    }

    private void startTrading() {
        LOGGER.info("Start trading");
        while (true) {
            stall.trade(buyers);
            BuyerService.inviteNewBuyers(buyers, storeSize);
        }
    }
}
