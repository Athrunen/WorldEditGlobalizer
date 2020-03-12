package me.illgilp.worldeditglobalizerbungee.message.template;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import me.illgilp.bukkit.configuration.InvalidConfigurationException;
import me.illgilp.bukkit.configuration.file.YamlConfiguration;
import me.illgilp.worldeditglobalizerbungee.message.MessageFile;
import net.md_5.bungee.api.ChatColor;

public class GermanMessageFile implements MessageFile {

    private File file;
    private YamlConfiguration configuration;
    private Map<String, Object> defaults = new HashMap<>();

    public GermanMessageFile(File file) {
        this.file = file;
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        configuration = new YamlConfiguration();
        try {
            configuration.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        addDefaults();
        configuration.addDefaults(defaults);
        configuration.options().copyDefaults(true);
        configuration.options().header("Alle Nachrichten können durch das Ersetzen der Nachricht mit 'none' deaktiviert werden.");
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLanguage() {
        return "de";
    }

    @Override
    public String getDefaultMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', getDefaultString(path) + "");
    }

    @Override
    public String getRawDefaultMessage(String path) {
        return getDefaultString(path) + "";
    }

    @Override
    public Set<String> getKeySet() {
        return defaults.keySet();
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', getString(path));
    }

    @Override
    public String getRawMessage(String path) {
        return getString(path);
    }

    private String getString(String path) {
        return ((configuration.getString(path) + "").equalsIgnoreCase("null") ? "none" : configuration.getString(path));
    }

    private String getDefaultString(String path) {
        return ((defaults.get(path) + "").equalsIgnoreCase("null") ? "none" : defaults.get(path) + "");
    }

    private void addDefaults() {
        defaults.put("timeFormat", "dd. MM. yyyy HH:mm:ss");

        defaults.put("clipboard.start.uploading", "&dDeine Zwischenablage wird hochgeladen...");
        defaults.put("clipboard.finish.uploading", "&dDeine Zwischenablage wurde hochgeladen! (Größe: {0})");

        defaults.put("clipboard.start.downloading", "&dDeine Zwischenablage wird auf den Server heruntergeladen, auf dem du dich gerade befindest...");
        defaults.put("clipboard.finish.downloading", "&dDeine Zwischenablage wurde heruntergeladen! (Größe: {0})");

        defaults.put("clipboard.error.downloading", "&cEs ist eine Fehler aufgetreten! Verwende '/weg download' um deine Zwischenablage herunterzuladen!");

        defaults.put("clipboard.clear", "&dDeine Zwischenablage ist nun leer!");

        defaults.put("clipboard.empty.own", "&cDeine Zwischenablage ist leer!");
        defaults.put("clipboard.empty.other", "&c{0}'s Zwischenablage ist leer!");

        defaults.put("clipboard.tooBig", "&cDeine Zwischenablage ist zu groß! (Max: {0} Zwischenablage: {1})");

        defaults.put("command.usage-message", "&7Falscher Syntax! Verwende: {0}");

        defaults.put("command.cannotUse", "&cDu kannst diesen Befehl auf diesem Server nicht ausführen!");

        defaults.put("command.permissionDenied", "&cDu hast keinen Zugriff auf diesen Befehl!");

        defaults.put("command.console", "&cNur Spieler können diesen Befehl ausführen!");

        defaults.put("timedOut", "&cDie aktuelle Aufgabe braucht länger als {0} Millisekunden und wird nun beendet!");

        defaults.put("command.start.reload", "&7Alle Configs und alle Nachrichten-Datein werden neugeladen...");
        defaults.put("command.finish.reload", "&aAlle Configs und alle Nachrichten-Datein wurden neugeladen!");

        defaults.put("command.playerNotFound", "&cDer Spieler '&6{0}&c' existiert nicht!");

        defaults.put("command.info.format", "&3Info's über &6{0}&3:\n" +
                "&6&l>> &r&aName &7= &f{1}\n" +
                "&6&l>> &r&aUUID &7= &f{2}\n" +
                "&6&l>> &r&aUpload Datum &7= &f{3}\n" +
                "&6&l>> &r&aZwischenablagen Größe &7= &f{4}");

        defaults.put("command.stats.format", "&3Statistiken:\n" +
                "&6&l>> &r&aGespeicherte Zwischenablagen &7= &f{0}\n" +
                "&6&l>> &r&aTotaler Speicherverbrauch &7= &f{1}\n" +
                "&6&l>> &r&aDurchschnittlicher Speicherverbrauch &7= &f{2}");

        defaults.put("command.schematic.list", "&7Verfügbare Schematics [{0}/{1}]:\n&2{2}");
        defaults.put("command.schematic.save", "&aDeine Zwischenablage wurde in eine Schematic-Datei gespeichert!");
        defaults.put("command.schematic.delete", "&aDie Schematic-Datei wurde gelöscht!");
        defaults.put("schematic.notFound", "&cEine Schematic-Datei mit diesem Namen existiert nicht!");
        defaults.put("command.schematic.load.success", "&aDie Schematic-Datei wurde in deine Zwischenablage geladen! Wenn der Download fertig ist, kannst du die Schematic mit '//paste' einfügen!");

        defaults.put("invalid.number", "&cBitte gib eine gültige Nummer an!");

        defaults.put("incompatible.version", "&cDie Version von dem BungeeCord-Plugin(&6{0}&c) ist nicht kompatibel mit der Version des Unterservers: &8'{1}'&c(&6{2}&c)!");

        defaults.put("clipboard.unknownFormat", "&cDie heruntergeladene Zwischenablage/Schematic ist in einem falschen Format, lösche diese um den Fehler zu beheben.");

        defaults.put("update.notify", "&3Es gibt eine neue Version von diesem Plugin!\n" +
                "&6&l>> &r&aAktuelle Version &7= &f{1}\n" +
                "&6&l>> &r&aNeuste Version &7= &f{2}\n" +
                "&6&l>> &r&aUpdate Nachricht &7= &f{3}\n" +
                "&6&l>> &r&aDownload Link &7= &f{4}");

        defaults.put("command.help.discord", "§7Du brauchst Hilfe? Joine einfach unserem Discord Server. §7https://discord.gg/B8BEaNV");
    }
}
