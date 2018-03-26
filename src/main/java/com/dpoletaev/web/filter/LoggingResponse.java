package com.dpoletaev.web.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class LoggingResponse extends HttpServletResponseWrapper {

    private LoggingServletOutputStream servletOutputStream;
    private HttpServletResponse response;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public LoggingResponse(HttpServletResponse response) throws IOException {
        super(response);
        this.response = response;
        this.servletOutputStream = new LoggingServletOutputStream();
    }

    @Override
    public ServletResponse getResponse() {
        return this;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return servletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(servletOutputStream.outputStream);
    }

    public String getBody() throws UnsupportedEncodingException {
        return servletOutputStream.outputStream.toString(
                response.getCharacterEncoding() == null
                        ? Charset.defaultCharset().name()
                        : response.getCharacterEncoding()
        );
    }

    private class LoggingServletOutputStream extends ServletOutputStream {

        private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        public boolean isReady() {
            return false;
        }

        public void setWriteListener(WriteListener listener) {

        }

        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            outputStream.write(b, off, len);
        }
    }
}
