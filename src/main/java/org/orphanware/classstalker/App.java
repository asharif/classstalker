package org.orphanware.classstalker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class App {

    private static final String SOURCE_PATH = "src/main/java/";
    
    private static Map<String, Long> sourceFiles = new HashMap<>();
    private static boolean recompileNeeded = false;

    public static void main(String[] args) throws InterruptedException, IOException {
        while (true) {
            System.out.println("watching source dir " + SOURCE_PATH);
            Thread.currentThread().sleep(1000);
            File root = new File(SOURCE_PATH);
            checkSourceFilesStatus(root);
            
            if( recompileNeeded ) {
                
                Runtime.getRuntime().exec("ant compile");
                recompileNeeded = false;
            }
            
        }
        
    }

    public static void checkSourceFilesStatus(File folder) {

        
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                
                if(sourceFiles.containsKey(listOfFiles[i].getAbsolutePath())) {
                    
                    
                    if(sourceFiles.containsKey(listOfFiles[i].getAbsolutePath())) {
                        Long oldTimeStamp = sourceFiles.get(listOfFiles[i].getAbsolutePath());
                        
                        if( oldTimeStamp < new Long(listOfFiles[i].lastModified())) {
                            
                            System.out.println("recompiling file: " + listOfFiles[i].getAbsolutePath());
                            recompileNeeded = true;
                            sourceFiles.put(listOfFiles[i].getAbsolutePath(), new Long(listOfFiles[i].lastModified()));
                        }
                    }
                    
                } else {
                    //add file and last modified date
                    sourceFiles.put(listOfFiles[i].getAbsolutePath(), new Long(listOfFiles[i].lastModified()));
                }
            
            } else if (listOfFiles[i].isDirectory()) {
            
                checkSourceFilesStatus(listOfFiles[i]);
            
            }
        }
    }
    

}
