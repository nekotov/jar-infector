package jlxip.JavaInfector;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Compress {

	public Compress(File PATH) {
		System.out.print("Re-compressing...");
		
		File[] files = PATH.listFiles();
		
		String toRun = "jar cfm "+Main.OUTPUT+" "+/*Main.DESTDIR+File.separator+*/"META-INF"+File.separator+"MANIFEST.MF ";
		for(int i=0;i<files.length;i++) {
			if(!files[i].equals("META-INF")) {
				toRun = toRun /*+Main.DESTDIR+File.separator*/+ files[i].getName() + " ";
			}
		}
		
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(toRun.split(" "));
			processBuilder.directory(new File(Main.DESTDIR));
			Process process = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
			    System.out.println(line);
			}
			
			Path source = Paths.get(Main.DESTDIR, Main.OUTPUT);
	        Path target = Paths.get(System.getProperty("user.dir"), Main.OUTPUT);
	        Files.move(source, target);
			
			removeFolder(PATH);
			System.out.println("Done!");
		} catch (Exception e) {
			System.out.println("FAILED!");
			e.printStackTrace();
		}
	}
	
	public void removeFolder(File PATH) {
		String[] entries = PATH.list();
		for(String s: entries) {
			File currentFile = new File(PATH.getPath(), s);
			if(currentFile.isDirectory()) {
				removeFolder(currentFile);
			} else {
				currentFile.delete();
			}
		}
		PATH.delete();
	}
}
