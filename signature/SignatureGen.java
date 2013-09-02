package org.shanon.api.signature;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignatureGen {
	
	public byte[] getSig() throws Exception {
	    SecretKeySpec sk = new SecretKeySpec(key.getBytes(), "HmacSHA1");

		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(sk);
		byte[] result = mac.doFinal(message.getBytes());
		
		return result;
	}
	
	private String key = "default";	
	public void setKey(String key){
		this.key = key;
	}
	
	private String message = "default";
	public void setMessage(String message){
		this.message = message;
	}
}
