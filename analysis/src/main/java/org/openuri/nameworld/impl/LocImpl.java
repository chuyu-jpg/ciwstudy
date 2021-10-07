/*
 * XML Type:  loc
 * Namespace: http://openuri.org/nameworld
 * Java type: Loc
 *
 * Automatically generated - do not modify.
 */
package org.openuri.nameworld.impl;

import org.openuri.nameworld.*;

import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
/**
 * An XML loc(@http://openuri.org/nameworld).
 *
 * This is a complex type.
 */
public class LocImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements Loc
{
    private static final long serialVersionUID = 1L;


    public LocImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REFERENCE$0 = 
        new javax.xml.namespace.QName("http://openuri.org/nameworld", "reference");
    private static final javax.xml.namespace.QName NAME$2 = 
        new javax.xml.namespace.QName("", "name");
    
    
    /**
     * Gets array of all "reference" elements
     */
    public Reference[] getReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(REFERENCE$0, targetList);
            Reference[] result = new Reference[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "reference" element
     */
    public Reference getReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            Reference target = null;
            target = (Reference)get_store().find_element_user(REFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "reference" element
     */
    public int sizeOfReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REFERENCE$0);
        }
    }
    
    /**
     * Sets array of all "reference" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setReferenceArray(Reference[] referenceArray)
    {
        check_orphaned();
        arraySetterHelper(referenceArray, REFERENCE$0);
    }
    
    /**
     * Sets ith "reference" element
     */
    public void setReferenceArray(int i, Reference reference)
    {
        generatedSetterHelperImpl(reference, REFERENCE$0, i, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_ARRAYITEM);
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "reference" element
     */
    public Reference insertNewReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            Reference target = null;
            target = (Reference)get_store().insert_element_user(REFERENCE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "reference" element
     */
    public Reference addNewReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            Reference target = null;
            target = (Reference)get_store().add_element_user(REFERENCE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "reference" element
     */
    public void removeReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REFERENCE$0, i);
        }
    }
    
    /**
     * Gets the "name" attribute
     */
    public java.lang.String getName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "name" attribute
     */
    public org.apache.xmlbeans.XmlString xgetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$2);
            return target;
        }
    }
    
    /**
     * True if has "name" attribute
     */
    public boolean isSetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(NAME$2) != null;
        }
    }
    
    /**
     * Sets the "name" attribute
     */
    public void setName(java.lang.String name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$2);
            }
            target.setStringValue(name);
        }
    }
    
    /**
     * Sets (as xml) the "name" attribute
     */
    public void xsetName(org.apache.xmlbeans.XmlString name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$2);
            }
            target.set(name);
        }
    }
    
    /**
     * Unsets the "name" attribute
     */
    public void unsetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(NAME$2);
        }
    }
    /**
     * An XML reference(@http://openuri.org/nameworld).
     *
     * This is a complex type.
     */


    public static class ReferenceImpl extends XmlComplexContentImpl implements Reference
    {
        private static final long serialVersionUID = 1L;

        public ReferenceImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TO$0 = 
            new javax.xml.namespace.QName("", "to");
        
        
        /**
         * Gets the "to" attribute
         */
        public javax.xml.namespace.QName getTo()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TO$0);
                if (target == null)
                {
                    return null;
                }
                return target.getQNameValue();
            }
        }
        
        /**
         * Gets (as xml) the "to" attribute
         */
        public org.apache.xmlbeans.XmlQName xgetTo()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlQName target = null;
                target = (org.apache.xmlbeans.XmlQName)get_store().find_attribute_user(TO$0);
                return target;
            }
        }
        
        /**
         * True if has "to" attribute
         */
        public boolean isSetTo()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(TO$0) != null;
            }
        }
        
        /**
         * Sets the "to" attribute
         */
        public void setTo(javax.xml.namespace.QName to)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TO$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TO$0);
                }
                target.setQNameValue(to);
            }
        }
        
        /**
         * Sets (as xml) the "to" attribute
         */
        public void xsetTo(org.apache.xmlbeans.XmlQName to)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlQName target = null;
                target = (org.apache.xmlbeans.XmlQName)get_store().find_attribute_user(TO$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlQName)get_store().add_attribute_user(TO$0);
                }
                target.set(to);
            }
        }
        
        /**
         * Unsets the "to" attribute
         */
        public void unsetTo()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(TO$0);
            }
        }
    }
}
