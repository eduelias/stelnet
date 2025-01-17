package stelnet.widget.viewer;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionUtils;
import stelnet.util.StorageUtils;

@Log4j
/**
 * Displays storage content grouped by the market it is located at.
 */
public class PerMarketStrategy implements DisplayStrategy {

    @Override
    public List<LocationContent> getData(ButtonManager filteringButtons) {
        List<LocationContent> data = new LinkedList<>();
        List<SubmarketAPI> storages = StorageUtils.getAllSortedWithAccess();
        for (SubmarketAPI storage : storages) {
            processSubmarket(new LocationInfo(storage.getMarket()), storage, filteringButtons, data);
        }
        return data;
    }

    protected void processSubmarket(
        LocationInfo locationData,
        SubmarketAPI storage,
        ButtonManager filteringButtons,
        List<LocationContent> data
    ) {
        CargoAPI storageCargo = storage.getCargo();
        CargoAPI items = getItems(filteringButtons, storageCargo);
        List<FleetMemberAPI> ships = getShips(filteringButtons, storageCargo);
        String name = storage.getMarket().getName();
        log.debug("Found " + items.getStacksCopy().size() + " items in " + name);
        log.debug("Found " + ships.size() + " ships in " + name);
        data.add(new LocationContent(locationData, items, ships));
    }

    private CargoAPI getItems(ButtonManager filteringButtons, CargoAPI storageCargo) {
        CargoAPI items = storageCargo.createCopy();
        List<CargoStackAPI> cargoStacks = storageCargo.getStacksCopy();
        CollectionUtils.reduce(cargoStacks, filteringButtons.getFilters());
        CargoUtils.replaceCargoStacks(items, cargoStacks);
        return items;
    }

    private List<FleetMemberAPI> getShips(ButtonManager filteringButtons, CargoAPI storageCargo) {
        List<FleetMemberAPI> ships = storageCargo.getMothballedShips().getMembersInPriorityOrder();
        CollectionUtils.reduce(ships, filteringButtons.getFilters());
        return ships;
    }
}
