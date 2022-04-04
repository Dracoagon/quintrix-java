package com.quintrix.jfs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Print a file to say hello!
 */

public class HelloFile {
	
	public static void main( String[] args ) throws IOException {
		
		//check if file exists
		Path path = Paths.get("hello.txt");
		boolean doesFileExist = Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
	
		//create and write a file if it does not exist
		if (!doesFileExist) {
			System.out.println("You don't have a hello! Let's get you one.");
			List<String> lines = Arrays.asList("Hello World!");
			Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		}
		
		//read the file if it does exist
		else {
			System.out.println("You have a hello! Here it is:");
			String read = Files.readAllLines(path).get(0);
			System.out.println(read);
		}
	}
}