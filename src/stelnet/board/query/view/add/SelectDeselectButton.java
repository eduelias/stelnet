package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class SelectDeselectButton extends Button {

    public SelectDeselectButton(
        Enum<?> label,
        boolean isEnabled,
        final boolean desiredState,
        final FilteringButton[] buttons
    ) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_ROW_HEIGHT), L10n.get(label), isEnabled);
        setCutStyle(CutStyle.C2_MENU);
        setPadding(0);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    for (FilteringButton button : buttons) {
                        button.setStateOn(desiredState);
                    }
                }
            }
        );
        if (!isEnabled) {
            setTextColor(Misc.scaleAlpha(getTextColor(), 0));
            setBackgroundColor(Misc.scaleAlpha(getBackgroundColor(), 0));
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.setButtonFontVictor10();
        super.render(tooltip);
        tooltip.setButtonFontDefault();
    }
}
