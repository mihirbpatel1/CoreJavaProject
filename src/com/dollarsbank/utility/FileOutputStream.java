package com.dollarsbank.utility;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FileOutputStream extends ObjectOutputStream {

    private boolean output;
    private boolean initialized;
    private DataOutputStream dataOutput;

    protected FileOutputStream(boolean append) throws IOException, SecurityException {
        super();
        this.output = append;
        this.initialized = true;
    }

    public FileOutputStream(OutputStream out, boolean append) throws IOException {
        super(out);
        this.output = append;
        this.initialized = true;
        this.dataOutput = new DataOutputStream(out);
        this.writeStreamHeader();
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        if (!this.initialized || this.output) return;
        if (dataOutput != null) {
            dataOutput.writeShort(STREAM_MAGIC);
            dataOutput.writeShort(STREAM_VERSION);
        }
    }
}  

