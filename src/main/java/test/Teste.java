package test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class Teste {

	
	private static final String[] SCOPES = {DriveScopes.DRIVE};
	static final HttpTransport httpTransport = new NetHttpTransport();
	static final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
	
public static void main(String[] args) {

	
    
    
	
    

    GoogleCredential credential;
	try {
	    InputStream privateJsonStream = new FileInputStream("E:\\dev\\eclipse\\workspaces\\testConnGDriveServiceAccount\\src\\main\\java\\test\\credentials.json");
		credential = GoogleCredential.fromStream(privateJsonStream).createScoped(Arrays.asList(SCOPES));
			
			  Drive drv = new com.google.api.services.drive.Drive.Builder( httpTransport,
			  jsonFactory, null) .setHttpRequestInitializer(credential) .build();
			
			  /*
			 * for (Iterator iterator = drv.files().list().execute().getFiles().iterator();
			 * iterator.hasNext();) { File type = (File) iterator.next();
			 * System.out.println(type.getName());
			 * 
			 * }
			 */
		
			  drv.about();
			  
		FileList result = drv.files().list()
				//.setQ("mimeType = 'application/vnd.google-apps.folder'")
				.setQ("'1ImaK35t3suo2igYLDT3uig7aS7MC5bA_' in parents")
				
                //.setPageSize(10)
                //.setFields("parents")
                .execute();
		

		
        java.util.List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
                drv.files().get(file.getId());
                
                String fileId = "0B7KRlixss1QFcThMR2k3OFpaaWlncHFRUlpnSE1iXzhSOE5F";
                if(file.getId().equals(fileId)) {
                	//org.apache.commons.io.FileUtils.copyURLToFile(new URL(file.getWebContentLink()), new java.io.File("E:\\AAA.TXT"));
                	
                	//File f = drv.files().get("0B7KRlixss1QFcThMR2k3OFpaaWlncHFRUlpnSE1iXzhSOE5F").execute();

                	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                	drv.files().get(fileId)
                	    .executeMediaAndDownloadTo(outputStream);
                	FileOutputStream fos = new FileOutputStream(new java.io.File("e:\\dev\\aaa.doc"));
                	fos.write(outputStream.toByteArray());
                	fos.flush();
                	fos.close();
                }
                
            }
        }
        
       
	
	} catch (Exception e) {

		e.printStackTrace();
	}	
    
	
}



}
