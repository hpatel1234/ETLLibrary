package com.test.etlprocess.writer.impl;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class FileWriterImplTest {
     private FileWriterImpl testInstance = new FileWriterImpl();
     
     @Test
     public void testWrite() {
    	 testInstance.setSource(".");
    	 testInstance.setDestination(".");
    	 testInstance.write(new String[]{"i am author"});
    	 Assert.assertTrue(new File("./File1.out").exists());
     }
     
     @Test(expected = RuntimeException.class)
     public void testWriteWithNullSource() {
    	 testInstance.setSource(null);
    	 testInstance.setDestination(".");
     }
     
     @Test(expected = RuntimeException.class)
     public void testWriteWithNullDestination() {
    	 testInstance.setSource(".");
    	 testInstance.setDestination(".");
    	 testInstance.write(new String[]{"i am author"});
    	 Assert.assertTrue(new File("./File1.out").exists());
     }
}
