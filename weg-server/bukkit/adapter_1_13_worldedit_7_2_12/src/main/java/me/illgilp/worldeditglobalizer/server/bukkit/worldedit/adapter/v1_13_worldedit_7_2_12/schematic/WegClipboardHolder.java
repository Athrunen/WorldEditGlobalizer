package me.illgilp.worldeditglobalizer.server.bukkit.worldedit.adapter.v1_13_worldedit_7_2_12.schematic;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.session.ClipboardHolder;

public class WegClipboardHolder extends ClipboardHolder {

    private final int hashCode;

    public WegClipboardHolder(Clipboard clipboard, int hashcode) {
        super(clipboard);
        this.hashCode = hashcode;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
}
