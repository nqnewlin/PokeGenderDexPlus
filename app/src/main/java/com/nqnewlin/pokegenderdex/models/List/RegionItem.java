package com.nqnewlin.pokegenderdex.models.List;

import com.nqnewlin.pokegenderdex.models.RegionId;

public class RegionItem extends ListItem {
    RegionId regionId;

    public RegionItem(RegionId regionId) {
        this.regionId = regionId;
    }

    @Override
    public int getType() { return TYPE_HEADER; }

    public RegionId getRegionId() {
        return regionId;
    }


}
