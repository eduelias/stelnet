package stelnet.board.commodity.view;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.board.commodity.CommodityTab;
import stelnet.board.commodity.table.BuyTableContent;
import stelnet.board.commodity.table.SellTableContent;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.TabViewContainer;
import uilib.Table;
import uilib.TableContent;
import uilib.property.Size;

@RequiredArgsConstructor
public class TabViewFactory implements RenderableFactory {

    private final String commodityId;
    private final CommodityTab commodityTab;

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth() - 210;
        float height = size.getHeight() - 24;
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(new Size(width, height));
        addBuyTab(tabViewContainer, width);
        addSellTab(tabViewContainer, width);
        return Collections.<Renderable>singletonList(tabViewContainer);
    }

    private void addBuyTab(TabViewContainer tabViewContainer, float width) {
        CommodityTabButton tabButton = getTabButton(CommodityTab.BUY, Keyboard.KEY_B);
        Table table = getBuyTable(width);
        boolean isActive = isActive(CommodityTab.BUY);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private void addSellTab(TabViewContainer tabViewContainer, float width) {
        CommodityTabButton tabButton = getTabButton(CommodityTab.SELL, Keyboard.KEY_S);
        Table table = getSellTable(width);
        boolean isActive = isActive(CommodityTab.SELL);
        tabViewContainer.addTab(tabButton, table, isActive);
    }

    private Table getBuyTable(float width) {
        List<MarketAPI> markets = CommodityTab.BUY.getMarkets(commodityId);
        TableContent tableContent = new BuyTableContent(commodityId, markets);
        return new Table(commodityId, width, 0, tableContent);
    }

    private Table getSellTable(float width) {
        List<MarketAPI> markets = CommodityTab.SELL.getMarkets(commodityId);
        TableContent tableContent = new SellTableContent(commodityId, markets);
        return new Table(commodityId, width, 0, tableContent);
    }

    private CommodityTabButton getTabButton(CommodityTab currentTab, int keyboardShortcut) {
        return new CommodityTabButton(currentTab, commodityTab, keyboardShortcut);
    }

    private boolean isActive(CommodityTab currentTab) {
        return currentTab.equals(commodityTab);
    }
}
