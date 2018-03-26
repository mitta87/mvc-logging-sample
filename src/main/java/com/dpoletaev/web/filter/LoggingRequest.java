package com.dpoletaev.web.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class LoggingRequest extends HttpServletRequestWrapper {
    private byte[] rawBody;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public LoggingRequest(HttpServletRequest request) {
        super(request);
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new LoggingServletInputStream(new BufferedInputStream(new ByteArrayInputStream(getRawBody())));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    private byte[] getRawBody() throws IOException {
        if (rawBody == null) {
            rawBody = IOUtils.toByteArray(super.getInputStream());
        }
        return rawBody;
    }

    public String getRequestBody() throws IOException {
        return new String(getRawBody());
    }

    private class LoggingServletInputStream extends ServletInputStream {
        private InputStream is;

        private LoggingServletInputStream(InputStream is) {
            this.is = is;
        }

        public int read() throws IOException {
            return this.is.read();
        }

        public boolean isFinished() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public void setReadListener(ReadListener listener) {

        }

        @Override
        public void close() throws IOException {
            super.close();
            this.is.close();
        }
    }
}
