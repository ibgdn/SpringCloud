package com.ibgdn.openfeign;

import com.ibgdn.commons.model.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class OpenFeignServiceFallBackFactory implements FallbackFactory<OpenFeignService> {
    /**
     * Returns an instance of the fallback appropriate for the given cause
     *
     * @param cause corresponds to {@link AbstractCommand#getExecutionException()}
     *              often, but not always an instance of {@link FeignException}.
     */
    @Override
    public OpenFeignService create(Throwable cause) {
        return new OpenFeignService() {
            @Override
            public String openFeignServiceGet() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Class: ").append(this.getClass().getName())
                        .append(", Method: ").append(Thread.currentThread().getStackTrace()[1].getMethodName());
                return stringBuilder.toString();
            }

            @Override
            public String openFeignServiceProviderGet(String name) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Class: ").append(this.getClass().getName())
                        .append(", Method: ").append(Thread.currentThread().getStackTrace()[1].getMethodName());
                return stringBuilder.toString();
            }

            @Override
            public User openFeignServiceProviderPost(User user) {
                return null;
            }

            @Override
            public User openFeignServiceProviderPut(User user) {
                return null;
            }

            @Override
            public void openFeignServiceProviderDelete(Integer id) {

            }

            @Override
            public void openFeignServiceProviderDeleteRequestHeader(String name) throws UnsupportedEncodingException {

            }
        };
    }
}
