package com.dasanjos.java;

import java.io.File;
import java.io.FileInputStream;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;


/**
 * 
 * Uploads cv.pdf to ZANOX REST Service (http://cvu.zanox.com/) using Jersey
 * 
 */
public class RestClient {

	static final String URL = "http://cvu.zanox.com/";
	static final String PATH = "rest/upload/cv";

	public static void main(String[] args) throws Exception {
		WebResource webResource = Client.create().resource(URL);

		FormDataMultiPart form = new FormDataMultiPart();
		form.field("file", "cv.pdf");
		form.field("firstname", "Daniel");
		form.field("lastname", "Anjos");
		form.field("email", "dasanjos@gmail.com");
		form.field("source", "recruiter: Daniel Dynybil");

		File file = new File("cv.pdf");
		System.out.println("Uploading file: " + file.getAbsolutePath());
		FormDataBodyPart fdp = new FormDataBodyPart("file",
				new FileInputStream(file),
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
		form.bodyPart(fdp);

		System.out.println(webResource.path(PATH).accept(MediaType.TEXT_HTML)
				.type(MediaType.MULTIPART_FORM_DATA).post(String.class, form));
	}
}
