/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.shanon.api.signature;

import java.io.Serializable;

import org.apache.commons.codec.binary.Hex;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

/**
 * This TestBean is just an example about how to write testbeans. The intent is
 * to demonstrate usage of the TestBean features to podential TestBean
 * developers. Note that only the class's introspector view matters: the methods
 * do nothing -- nothing useful, in any case.
 */
public class SMPSignature extends AbstractSampler implements TestBean, Serializable {

	private static final long serialVersionUID = 5040077565901342193L;
	private static final Logger log = LoggingManager.getLoggerForClass();
 
	public SampleResult sample(Entry e) {
		
        SignatureGen sg = new SignatureGen();
        String signature ="NULL";
        

        SampleResult res = new SampleResult();
        res.setSampleLabel(getName());
        res.setSamplerData("signature");
        res.sampleStart();
        // Do something ...
        
        //シグネチャ作る
        sg.setKey(getSecretKey());
        sg.setMessage(getMessage());
        
		try {
			signature = new String(Hex.encodeHex(sg.getSig()));
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			log.error(e1.toString());
		}

    	final JMeterContext context = getThreadContext();
        JMeterVariables threadVars = context.getVariables();
        
        try{
            threadVars.put("api_sig", signature); //Jmeterのkey, value
        }
        catch(Exception ex){
        	log.error(ex.toString());
        }
        
        res.setResponseData(threadVars.get("api_sig").getBytes());
        res.setDataType(SampleResult.TEXT);
        res.sampleEnd();
        res.setSuccessful(true);
        return res;
    }

    // A TestBean is a Java Bean. Just define some properties and they will
    // automagically show up in the GUI.
    // A String property:  
    
	
    private String message;
    public String getMessage(){
    	return message;
    }
    public void setMessage(String s){
    	this.message = s;
    }
    
    private String secretKey;
    public String getSecretKey(){
    	return secretKey;
    }
    public void setSecretKey(String s){
    	this.secretKey = s;
    }
 }
