package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import uilib.EventHandler;

public class ToggleButton extends ControlButton {

    public ToggleButton(final Query query) {
        super(QueryL10n.ENABLED, QueryL10n.DISABLED, true, query.isEnabled());
        setCutStyle(CutStyle.C2_MENU);
        scaleButton(query);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    query.setEnabled(!query.isEnabled());
                    query.refresh();
                }
            }
        );
    }
}
