package org.nervos.appchain.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.nervos.appchain.protocol.core.Request;
import org.nervos.appchain.protocol.core.Response;
import org.nervos.appchain.utils.Async;

/**
 * Base service implementation.
 */
public abstract class Service implements AppChainjService {

    protected final ObjectMapper objectMapper;

    public Service(boolean includeRawResponses) {
        objectMapper = ObjectMapperFactory.getObjectMapper(includeRawResponses);
    }

    protected abstract InputStream performIO(String payload) throws IOException;

    @Override
    public <T extends Response> T send(
            Request request, Class<T> responseType) throws IOException {
        String payload = objectMapper.writeValueAsString(request);

        try (InputStream result = performIO(payload)) {
            if (result != null) {
                return objectMapper.readValue(result, responseType);
            } else {
                return null;
            }
        }
    }

    @Override
    public <T extends Response> Future<T> sendAsync(
            final Request jsonRpc20Request, final Class<T> responseType) {
        return Async.run(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return Service.this.send(jsonRpc20Request, responseType);
            }
        });
    }
}
