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

import java.beans.PropertyDescriptor;
import org.apache.jmeter.testbeans.BeanInfoSupport;

public class SMPSignatureBeanInfo extends BeanInfoSupport {
	
	private static final String MESSAGE = "message";
	private static final String SECRET_KEY = "secretKey";
	
    public SMPSignatureBeanInfo() {
        super(SMPSignature.class);
        
    	createPropertyGroup("smp_signature", new String[] { MESSAGE, SECRET_KEY});
    	
    	PropertyDescriptor p = property(MESSAGE);
    	p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    	p.setValue(DEFAULT, "Input api seed");
    	p.setValue(NOT_EXPRESSION, Boolean.TRUE);

    	p= property(SECRET_KEY);
    	p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    	p.setValue(DEFAULT, "Input Secret Key");
    	p.setValue(NOT_EXPRESSION, Boolean.TRUE);
    }
}
