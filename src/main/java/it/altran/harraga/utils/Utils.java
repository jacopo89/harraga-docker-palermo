package it.altran.harraga.utils;

import org.apache.commons.io.FileUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Random;

public class Utils {
	public static final boolean DUMMY= true;

	public static boolean deleteFile(Path path) {
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		return true;
	}

	public static boolean deleteFolder(String path) {
		try {
		    FileUtils.deleteDirectory(new File(path));
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		return true;
	}


	public static String saveFile(String stringFile, Long socialCardId, String nomeAllegato, String sectionName, String basePath) {

		String filePath = basePath + File.separator + socialCardId + File.separator + sectionName + File.separator + nomeAllegato;
		//TODO GESTIRE I CASI IN CUI IL FILA GI ESISTE RESITUENDO IL NUOVO NOME FILE

		String[] parts = stringFile.split(",");

		String fileContent = parts[1];

		FileOutputStream fos;
		try {

			File file = new File(filePath);
			 if (! 	file.getParentFile().exists()){
				 file.getParentFile().mkdirs();
			 }


			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}else {
				int index=1;

				String[] tokens = nomeAllegato.split("\\.(?=[^\\.]+$)");
				String nome = tokens[0];
				String estensione = tokens[1];

				do {

					nomeAllegato=nome+"_"+index+"."+estensione;
					filePath = basePath + File.separator + socialCardId + File.separator + sectionName + File.separator + nomeAllegato;
					file = new File(filePath);
					index++;
				}while(file.exists());
			}



			byte[] encoded = DatatypeConverter.parseBase64Binary(fileContent);

			//String encoded = DatatypeConverter.  printBase64Binary(fileContent.getBytes("UTF-8"));

			fos = new FileOutputStream(file);
			fos.write(encoded);

			//fos.write(Base64.getDecoder().decode(fileContent));
			fos.flush();
			fos.close();
			System.out.println(file.getAbsolutePath());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return sectionName + File.separator + nomeAllegato;
	}

	public static String getRandomToken() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	public static String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}


}
