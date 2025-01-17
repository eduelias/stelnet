package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import uilib.EventHandler;

public class PreviewButton extends ControlButton {

    public PreviewButton(final Query query) {
        super(QueryL10n.PREVIEWING, QueryL10n.PREVIEW, true, query.isSelected());
        setCutStyle(CutStyle.C2_MENU);
        scaleButton(query);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    query.select();
                }
            }
        );
    }
}
