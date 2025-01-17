package stelnet.board.query.view.add;

import java.util.Set;
import lombok.Getter;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

@Getter
public class FilteringButton extends AreaCheckbox {

    private boolean isVisible = true;
    private String filteringButtonId;
    private final Filter filter;

    public FilteringButton(Enum<?> translationId, Filter filter) {
        this(L10n.get(translationId), filter);
    }

    public FilteringButton(String translatedString, Filter filter) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_BUTTON_HEIGHT), translatedString, true, false);
        this.filter = filter;
        setPadding(0);
    }

    public FilteringButton(String translatedString, Filter filter, String filteringButtonId) {
        this(translatedString, filter);
        this.filteringButtonId = filteringButtonId;
    }

    public void updateVisibility(Set<String> visibleButtonIds) {
        isVisible = visibleButtonIds.contains(filteringButtonId);
    }
}
