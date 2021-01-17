package com.shadorc.shadbot.api.json.gamestats.diablo.hero;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadorc.shadbot.utils.StringUtil;
import reactor.util.annotation.Nullable;

import java.util.Optional;

public class HeroResponse {

    @Nullable
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("class")
    private String className;
    @JsonProperty("stats")
    private HeroStats stats;

    public Optional<String> getCode() {
        return Optional.ofNullable(this.code);
    }

    public String getName() {
        return this.name;
    }

    public String getClassName() {
        return StringUtil.capitalize(this.className.replace("-", " "));
    }

    public HeroStats getStats() {
        return this.stats;
    }

}
