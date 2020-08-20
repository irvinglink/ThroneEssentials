package com.github.irvinglink.ThroneEssentials;

import com.google.common.base.Charsets;

import java.util.UUID;

public class CommonUse {

    public UUID getOfflineUUID(String name) {
        return UUID.nameUUIDFromBytes(String.format("OfflinePlayer:%s", name).getBytes(Charsets.UTF_8));
    }

}
