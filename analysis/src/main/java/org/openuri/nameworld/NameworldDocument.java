/*
 * An XML document type.
 * Localname: nameworld
 * Namespace: http://openuri.org/nameworld
 * Java type: NameworldDocument
 *
 * Automatically generated - do not modify.
 */
package org.openuri.nameworld;


/**
 * A document containing one nameworld(@http://openuri.org/nameworld) element.
 *
 * This is a complex type.
 */
public interface NameworldDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(NameworldDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sCB9CB6F02CA30BB5B3155DB2A2800548").resolveHandle("nameworld2910doctype");
    
    /**
     * Gets the "nameworld" element
     */
    Nameworld getNameworld();
    
    /**
     * Sets the "nameworld" element
     */
    void setNameworld(Nameworld nameworld);
    
    /**
     * Appends and returns a new empty "nameworld" element
     */
    NameworldDocument.Nameworld addNewNameworld();
    
    /**
     * An XML nameworld(@http://openuri.org/nameworld).
     *
     * This is a complex type.
     */
    public interface Nameworld extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Nameworld.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sCB9CB6F02CA30BB5B3155DB2A2800548").resolveHandle("nameworld001delemtype");
        
        /**
         * Gets array of all "island" elements
         */
        NameworldDocument.Nameworld.Island[] getIslandArray();
        
        /**
         * Gets ith "island" element
         */
        NameworldDocument.Nameworld.Island getIslandArray(int i);
        
        /**
         * Returns number of "island" element
         */
        int sizeOfIslandArray();
        
        /**
         * Sets array of all "island" element
         */
        void setIslandArray(NameworldDocument.Nameworld.Island[] islandArray);
        
        /**
         * Sets ith "island" element
         */
        void setIslandArray(int i, NameworldDocument.Nameworld.Island island);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "island" element
         */
        NameworldDocument.Nameworld.Island insertNewIsland(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "island" element
         */
        NameworldDocument.Nameworld.Island addNewIsland();
        
        /**
         * Removes the ith "island" element
         */
        void removeIsland(int i);
        
        /**
         * An XML island(@http://openuri.org/nameworld).
         *
         * This is a complex type.
         */
        public interface Island extends org.apache.xmlbeans.XmlObject
        {
            public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
                org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Island.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sCB9CB6F02CA30BB5B3155DB2A2800548").resolveHandle("island5c0celemtype");
            
            /**
             * Gets array of all "location" elements
             */
            Loc[] getLocationArray();
            
            /**
             * Gets ith "location" element
             */
            Loc getLocationArray(int i);
            
            /**
             * Returns number of "location" element
             */
            int sizeOfLocationArray();
            
            /**
             * Sets array of all "location" element
             */
            void setLocationArray(Loc[] locationArray);
            
            /**
             * Sets ith "location" element
             */
            void setLocationArray(int i, Loc location);
            
            /**
             * Inserts and returns a new empty value (as xml) as the ith "location" element
             */
            Loc insertNewLocation(int i);
            
            /**
             * Appends and returns a new empty value (as xml) as the last "location" element
             */
            Loc addNewLocation();
            
            /**
             * Removes the ith "location" element
             */
            void removeLocation(int i);
            
            /**
             * Gets the "targetNamespace" attribute
             */
            java.lang.String getTargetNamespace();
            
            /**
             * Gets (as xml) the "targetNamespace" attribute
             */
            org.apache.xmlbeans.XmlString xgetTargetNamespace();
            
            /**
             * True if has "targetNamespace" attribute
             */
            boolean isSetTargetNamespace();
            
            /**
             * Sets the "targetNamespace" attribute
             */
            void setTargetNamespace(java.lang.String targetNamespace);
            
            /**
             * Sets (as xml) the "targetNamespace" attribute
             */
            void xsetTargetNamespace(org.apache.xmlbeans.XmlString targetNamespace);
            
            /**
             * Unsets the "targetNamespace" attribute
             */
            void unsetTargetNamespace();
            
            /**
             * A factory class with static methods for creating instances
             * of this type.
             */
            
            public static final class Factory
            {
                public static NameworldDocument.Nameworld.Island newInstance() {
                  return (NameworldDocument.Nameworld.Island) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
                
                public static NameworldDocument.Nameworld.Island newInstance(org.apache.xmlbeans.XmlOptions options) {
                  return (NameworldDocument.Nameworld.Island) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
                
                private Factory() { } // No instance of this class allowed
            }
        }
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static NameworldDocument.Nameworld newInstance() {
              return (NameworldDocument.Nameworld) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static NameworldDocument.Nameworld newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (NameworldDocument.Nameworld) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static NameworldDocument newInstance() {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static NameworldDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static NameworldDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static NameworldDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static NameworldDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static NameworldDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static NameworldDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static NameworldDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static NameworldDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static NameworldDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static NameworldDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static NameworldDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static NameworldDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static NameworldDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static NameworldDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static NameworldDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static NameworldDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static NameworldDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (NameworldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
