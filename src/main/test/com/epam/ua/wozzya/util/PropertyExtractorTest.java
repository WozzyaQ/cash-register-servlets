package com.epam.ua.wozzya.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PropertyExtractorTest {

    private Path tempFilePath;
    private static final String BROKEN_PATH = "";
    private static final String EXISTING_KEY = "key";
    private static final String EXISTING_VALUE = "value";
    private static final String NON_EXISTING_KEY = "not.exists";

    @Before
    public void setUp() throws IOException {
        tempFilePath = Files.createTempFile("temp",".properties");
        Files.write(tempFilePath, (EXISTING_KEY + "=" + EXISTING_VALUE).getBytes(StandardCharsets.UTF_8));
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFilePath);
    }

    @Test
    public void shouldReadPropertyFile() throws IOException {
        String actualValue = PropertyExtractor.extractByKey(EXISTING_KEY, tempFilePath.toString());
        assertEquals(EXISTING_VALUE, actualValue);
    }

    @Test(expected = IOException.class)
    public void shouldThrowIoExceptionWhenReadingFromBrokenPath() throws IOException, SQLException {
        PropertyExtractor.extractByKey(EXISTING_KEY, BROKEN_PATH);
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEWhenNoPropertyFound() throws IOException {
        PropertyExtractor.extractByKey(NON_EXISTING_KEY, tempFilePath.toString());
    }
}