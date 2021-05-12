//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.08 at 09:30:35 PM EDT 
//


package com.tracelink.prodsec.plugin.veracode.sast.api.data.detailedreport;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DynamicScanType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;simpleType name="DynamicScanType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="mp"/&gt;
 *     &lt;enumeration value="ds"/&gt;
 *     &lt;enumeration value="da"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "DynamicScanType", namespace = "https://www.veracode.com/schema/reports/export/1.0")
@XmlEnum
public enum DynamicScanType {

	@XmlEnumValue("mp")
	MP("mp"),
	@XmlEnumValue("ds")
	DS("ds"),
	@XmlEnumValue("da")
	DA("da");
	private final String value;

	DynamicScanType(String v) {
		value = v;
	}

	/**
	 * Gets the value of this scan type
	 *
	 * @return the scan type value
	 */
	public String value() {
		return value;
	}

	/**
	 * Returns the {@link DynamicScanType} that matches the given value
	 *
	 * @param v the value to get the scan type for
	 * @return the matching scan type
	 */
	public static DynamicScanType fromValue(String v) {
		for (DynamicScanType c : DynamicScanType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
