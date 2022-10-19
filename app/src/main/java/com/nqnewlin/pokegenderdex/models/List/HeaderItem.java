package com.nqnewlin.pokegenderdex.models.List;

import com.nqnewlin.pokegenderdex.models.RegionId;

public class HeaderItem extends ListItem {

    private RegionId regionId;

    @Override
    public int getType() {
        return TYPE_HEADER;
    }
}
