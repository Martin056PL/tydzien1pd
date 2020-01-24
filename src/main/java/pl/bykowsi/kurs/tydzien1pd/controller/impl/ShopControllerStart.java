package pl.bykowsi.kurs.tydzien1pd.controller.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import pl.bykowsi.kurs.tydzien1pd.controller.ShopController;
import pl.bykowsi.kurs.tydzien1pd.service.ShopService;

@Controller
@Profile("start")
public class ShopControllerStart implements ShopController {

    private ShopService service;

    public ShopControllerStart(@Qualifier("startShopService") ShopService service) {
        this.service = service;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        service.calculateFinalPrice();
    }


}
