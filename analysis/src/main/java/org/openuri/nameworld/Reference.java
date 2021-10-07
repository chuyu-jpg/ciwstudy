package org.openuri.nameworld;

public interface Reference extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Reference.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sCB9CB6F02CA30BB5B3155DB2A2800548").resolveHandle("reference1bf0elemtype");

    /**
     * Gets the "to" attribute
     */
    javax.xml.namespace.QName getTo();

    /**
     * Gets (as xml) the "to" attribute
     */
    org.apache.xmlbeans.XmlQName xgetTo();

    /**
     * True if has "to" attribute
     */
    boolean isSetTo();

    /**
     * Sets the "to" attribute
     */
    void setTo(javax.xml.namespace.QName to);

    /**
     * Sets (as xml) the "to" attribute
     */
    void xsetTo(org.apache.xmlbeans.XmlQName to);

    /**
     * Unsets the "to" attribute
     */
    void unsetTo();

    /**
     * A factory class with static methods for creating instances
     * of this type.
     */

    public static final class Factory
    {
        public static Reference newInstance() {
            return (Reference) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }

        public static Reference newInstance(org.apache.xmlbeans.XmlOptions options) {
            return (Reference) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }

        private Factory() { } // No instance of this class allowed
    }
}
