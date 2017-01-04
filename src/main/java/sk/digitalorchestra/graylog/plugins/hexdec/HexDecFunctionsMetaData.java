package sk.digitalorchestra.graylog.plugins.hexdec;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

/**
 * Implement the PluginMetaData interface here.
 */
public class HexDecFunctionsMetaData implements PluginMetaData {
    private static final String PLUGIN_PROPERTIES = "sk.digitalorchestra.graylog.plugins.graylog-plugin-hexdec-functions/graylog-plugin.properties";

    @Override
    public String getUniqueId() {
        return "sk.digitalorchestra.graylog.plugins.hexdec.HexDecFunctionsPlugin";
    }

    @Override
    public String getName() {
        return "Numeric conversions (hex, dec) pipeline functions";
    }

    @Override
    public String getAuthor() {
        return "Ľudovít Lučenič <ludovit.lucenic@digital-orchestra.sk>";
    }

    @Override
    public URI getURL() {
        return URI.create("https://github.com/llucenic/graylog-plugin-hexdec");
    }

    @Override
    public Version getVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "version",
        		Version.from(1, 1, 0));
    }

    @Override
    public String getDescription() {
        return "Plugin installs hex-to-dec and dec-to-hex numeric/string conversions as pipeline functions";
    }

    @Override
    public Version getRequiredVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "graylog.version",
        		Version.from(2, 1, 2));
    }

    @Override
    public Set<ServerStatus.Capability> getRequiredCapabilities() {
        return Collections.emptySet();
    }
}
