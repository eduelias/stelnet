package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoPickerListener;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FleetMemberPickerListener;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.board.query.Query;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.QueryManager;
import stelnet.board.query.QueryState;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.board.query.provider.QueryProvider;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.board.query.view.add.ShipQueryFactory;
import stelnet.filter.CargoStackIsStack;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import stelnet.filter.ShipHullIsHull;
import stelnet.util.L10n;

@RequiredArgsConstructor
public class QueryPickerListener implements CargoPickerListener, FleetMemberPickerListener {

    private final PickerDialog dialog;
    private final QueryFactory factory;
    private final String type;

    @Override
    public void pickedCargo(CargoAPI cargo) {
        Set<Filter> selectedFilters = new LinkedHashSet<>();
        cargo.sort();
        for (CargoStackAPI cargoStack : cargo.getStacksCopy()) {
            selectedFilters.add(new CargoStackIsStack(cargoStack));
        }
        addItemQuery(selectedFilters);
    }

    @Override
    public void pickedFleetMembers(List<FleetMemberAPI> members) {
        Set<Filter> selectedFilters = new LinkedHashSet<>();
        for (FleetMemberAPI member : members) {
            selectedFilters.add(new ShipHullIsHull(member.getHullSpec()));
        }
        addShipQuery(selectedFilters);
    }

    @Override
    public void cancelledCargoSelection() {
        dialog.dismiss(null);
    }

    @Override
    public void cancelledFleetMemberPicking() {
        dialog.dismiss(null);
    }

    @Override
    public void recreateTextPanel(
        TooltipMakerAPI panel,
        CargoAPI cargo,
        CargoStackAPI pickedUp,
        boolean pickedUpFromSource,
        CargoAPI combined
    ) {}

    private void addItemQuery(Set<Filter> selectedFilters) {
        Set<Filter> filters = new LinkedHashSet<>();
        filters.add(new LogicalOr(selectedFilters, L10n.get(CommonL10n.ITEMS)));
        addQuery(filters);
    }

    private void addShipQuery(Set<Filter> selectedFilters) {
        Set<Filter> filters = new LinkedHashSet<>();
        filters.add(new LogicalOr(selectedFilters, L10n.get(CommonL10n.SHIPS)));
        ShipQueryFactory elevatedFactory = (ShipQueryFactory) factory;
        elevatedFactory.addDmodFilters(filters);
        addQuery(filters);
    }

    private void addQuery(Set<Filter> filters) {
        QueryBoard board = QueryBoard.getInstance(QueryBoard.class);
        QueryState state = board.getState();
        QueryManager manager = state.getQueryManager();
        QueryProvider provider = factory.getProvider();
        Query query = new Query(manager, provider, filters, type);
        manager.addQuery(query);
        state.setActiveTab(QueryBoardTab.LIST);
        dialog.dismiss(board);
    }
}
