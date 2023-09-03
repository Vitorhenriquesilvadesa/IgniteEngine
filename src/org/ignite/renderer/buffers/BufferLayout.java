package org.ignite.renderer.buffers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.ignite.core.app.Application.ClientLog;

public class BufferLayout implements Iterable<BufferElement> {

    private List<BufferElement> elements;
    private int stride = 0;

    public BufferLayout() {
    }

    public BufferLayout(List<BufferElement> elements) {
        this.elements = elements;
        this.calculateOffsetAndStride();
    }

    public BufferLayout(BufferElement[] elements) {
        this.elements = Arrays.asList(elements);
        this.calculateOffsetAndStride();
    }

    private void calculateOffsetAndStride() {
        int offset = 0;
        this.stride = 0;

        for (BufferElement element : this) {
            element.offset = offset;
            offset += element.size;
            this.stride += element.size;
        }
    }

    public List<BufferElement> getElements() {
        return this.elements;
    }

    public int getStride() {
        return stride;
    }

    @Override
    public Iterator<BufferElement> iterator() {
        return this.elements.iterator();
    }
}
