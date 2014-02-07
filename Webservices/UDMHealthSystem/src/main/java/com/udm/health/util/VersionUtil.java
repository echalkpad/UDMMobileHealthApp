package com.udm.health.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class VersionUtil {

	/*
	 * This probably only works in JBoss, but who knows.
	 */
	public static String getVersion() {
		String version = "";
		ProtectionDomain protectionDomain = VersionUtil.class.getProtectionDomain();
		
		if (protectionDomain != null) {
			CodeSource codeSource = protectionDomain.getCodeSource();
			
			if (codeSource != null) {
				URL url = codeSource.getLocation();
				
				if (url != null) {
					int webInfIndex = url.toString().indexOf("/WEB-INF");
					
					if (webInfIndex > 0) {
						String newPath = url.toString().substring(0, webInfIndex);
						
						try {
							InputStream stream = new URL(newPath + "/" + JarFile.MANIFEST_NAME).openStream();
							
							if (stream != null) {
								version = new Manifest(stream).getMainAttributes().getValue("Implementation-Version");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		}
		
		return version;
	}
	
}
