package com.yoshio3.services;

import com.yoshio3.rest.entities.storage.BlobStorageEntity;
import com.yoshio3.services.util.PropertyReaderService;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoterada
 */
public class StorageServiceTest {

    /**
     * Test of uploadFile method, of class StorageService.
     */
    @Test
    public void testUploadFile() throws Exception {
        System.out.println("uploadFile");
        StorageService instance = new StorageService();
        //Initialize the Blob Container
        instance.init();
        //At first delete All Files before creating the file
        instance.deleteAll(PropertyReaderService.getPropertyValue("STORAGE_CONTAINER_NAME"));
        List<BlobStorageEntity> beforeUp = instance.getAllFiles();
        System.out.println("Number of File1 :" + beforeUp.size());
        assertEquals(0, beforeUp.size());

        //Prepare the file      
        byte[] file = Files.readAllBytes(Paths.get("./README.md"));
        String fileName = "README.md";
        instance.uploadFile(file, fileName);
        System.out.println("Upload File Name:" + fileName);

        List<BlobStorageEntity> afterUp = instance.getAllFiles();
        assertEquals(1, afterUp.size());
        System.out.println("Number of File2 :" + afterUp.size());

        BlobStorageEntity blobEntry = afterUp.get(0);
        assertEquals(fileName, blobEntry.getName());
        System.out.println("Upload File Name:" + fileName);
        assertEquals(file.length, blobEntry.getSize());
        System.out.println("Upload File Size:" + blobEntry.getSize());
        instance.deleteAll(PropertyReaderService.getPropertyValue("STORAGE_CONTAINER_NAME"));
    }
}
