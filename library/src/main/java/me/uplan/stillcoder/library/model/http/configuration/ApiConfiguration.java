package me.uplan.stillcoder.library.model.http.configuration;

/**
 * @descript:http通信的配置信息
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public class ApiConfiguration {
    private String baseUrl;

    public static Builder builder() {
        return new Builder();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    private ApiConfiguration(Builder builder) {
        initialize(builder);
    }

    private void initialize(final Builder builder) {
        this.baseUrl = builder.baseUrl;
    }

    public static final class Builder {
        private String baseUrl;

        private Builder() {
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public ApiConfiguration build() {
            return new ApiConfiguration(this);
        }
    }
}
