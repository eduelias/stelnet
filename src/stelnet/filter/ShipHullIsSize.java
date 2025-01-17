package stelnet.filter;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;

@RequiredArgsConstructor
public class ShipHullIsSize extends ShipHullFilter {

    private final HullSize hullSize;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return hullSize.equals(shipHull.getHullSize());
    }

    @Override
    public String toString() {
        return L10n.get(hullSize);
    }
}
