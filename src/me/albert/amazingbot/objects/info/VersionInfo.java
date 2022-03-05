package me.albert.amazingbot.objects.info;

import com.google.gson.annotations.SerializedName;

public class VersionInfo {
    protected String app_name;
    protected String app_version;
    protected String app_full_name;
    protected String protocol_version;
    protected String coolq_edition;
    protected String coolq_directory;
    @SerializedName("go-cqhttp")
    protected boolean goCQhttp;
    protected String plugin_version;
    protected int plugin_build_number;
    protected String plugin_build_configuration;
    protected String runtime_version;
    protected String runtime_os;
    protected String version;
    protected int protocol;

    public String getAppName() {
        return app_name;
    }

    public String getAppVersion() {
        return app_version;
    }

    public String getAppFullName() {
        return app_full_name;
    }

    public String getProtocolVersion() {
        return protocol_version;
    }

    public String getCoolqEdition() {
        return coolq_edition;
    }

    public String getCoolqDirectory() {
        return coolq_directory;
    }

    public boolean isGoCQhttp() {
        return goCQhttp;
    }

    public String getPluginVersion() {
        return plugin_version;
    }

    public int getPluginBuildNumber() {
        return plugin_build_number;
    }

    public String getPluginBuildConfiguration() {
        return plugin_build_configuration;
    }

    public String getRuntimeVersion() {
        return runtime_version;
    }

    public String getRuntimeOS() {
        return runtime_os;
    }

    public String getVersion() {
        return version;
    }

    public int getProtocol() {
        return protocol;
    }

}
