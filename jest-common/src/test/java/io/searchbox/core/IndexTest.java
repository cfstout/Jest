package io.searchbox.core;

import io.searchbox.params.Parameters;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Dogukan Sonmez
 */


public class IndexTest {

    @Test
    public void indexDocument() {
        Index index = new Index.Builder(new Object()).index("twitter").type("tweet").id("1").build();
        assertEquals("PUT", index.getRestMethodName());
        assertEquals("twitter/tweet/1", index.getURI());
    }

    @Test
    public void indexDocumentWithVersionParameter() {
        Index index = new Index.Builder(new Object())
                .index("twitter")
                .type("tweet")
                .id("1")
                .setParameter(Parameters.VERSION, 3)
                .build();
        assertEquals("PUT", index.getRestMethodName());
        assertEquals("twitter/tweet/1?version=3", index.getURI());
    }

    @Test
    public void indexDocumentWithoutId() {
        Index index = new Index.Builder(new Object()).index("twitter").type("tweet").build();
        assertEquals("POST", index.getRestMethodName());
        assertEquals("twitter/tweet", index.getURI());
    }

    @Test
    public void equals() {
        Object source = new Object();
        Index index1 = new Index.Builder(source).index("twitter").type("tweet").id("1").build();
        Index index1Duplicate = new Index.Builder(source).index("twitter").type("tweet").id("1").build();

        assertEquals(index1, index1Duplicate);
    }

    @Test
    public void equalsReturnsFalseForDifferentSources() {
        Index index1 = new Index.Builder(new Object()).index("twitter").type("tweet").id("1").build();
        Index index2 = new Index.Builder(new Object()).index("twitter").type("tweet").id("1").build();

        assertNotEquals(index1, index2);
    }

}
