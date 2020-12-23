//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.08 at 09:30:33 PM EDT 
//


package com.tracelink.prodsec.plugin.veracode.sast.api.data.applist;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoginAccountType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;simpleType name="LoginAccountType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="user"/&gt;
 *     &lt;enumeration value="api"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "LoginAccountType", namespace = "https://analysiscenter.veracode.com/schema/2.0/applist")
@XmlEnum
public enum LoginAccountType {

	@XmlEnumValue("user")
	USER("user"),
	@XmlEnumValue("api")
	API("api");
	private final String value;

	LoginAccountType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static LoginAccountType fromValue(String v) {
		for (LoginAccountType c : LoginAccountType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
