package stelnet.commodity.data;

import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.helper.GlobalHelper;

public class SupplyPrice implements Price {

    private final String commodityId;
    private final float econUnit;

    public SupplyPrice(String commodityId) {
        this.commodityId = commodityId;
        EconomyAPI economy = GlobalHelper.getEconomy();
        this.econUnit = economy.getCommoditySpec(commodityId).getEconUnit();
    }

    @Override
    public float getPrice(MarketAPI market) {
        return market.getSupplyPrice(commodityId, econUnit, true) / econUnit;
    }
}
