/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
/*
 * This class was automatically generated with
 * <a href="http://www.castor.org">Castor 0.9.4.3</a>, using an XML
 * Schema.
 * $Id: GroupDescriptor.java,v 1.1.1.1 2009-08-20 12:23:31 spuhl Exp $
 */
package de.cismet.cids.admin.analyze.castorGenerated;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import org.exolab.castor.mapping.AccessMode;
import org.exolab.castor.xml.TypeValidator;
import org.exolab.castor.xml.XMLFieldDescriptor;
import org.exolab.castor.xml.validators.*;

/**
 * Class GroupDescriptor.
 *
 * @version  $Revision: 1.1.1.1 $ $Date: 2009-08-20 12:23:31 $
 */
public class GroupDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {

    //~ Instance fields --------------------------------------------------------

    // --------------------------/
    // - Class/Member Variables -/
    // --------------------------/

    /** Field nsPrefix. */
    private java.lang.String nsPrefix;

    /** Field nsURI. */
    private java.lang.String nsURI;

    /** Field xmlName. */
    private java.lang.String xmlName;

    /** Field identity. */
    private org.exolab.castor.xml.XMLFieldDescriptor identity;

    //~ Constructors -----------------------------------------------------------

    /**
     * ----------------/ - Constructors -/ ----------------/
     */
    public GroupDescriptor() {
        super();
        nsURI = "http://www.cismet.de/cids";
        xmlName = "group";

        // -- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl desc = null;
        org.exolab.castor.xml.XMLFieldHandler handler = null;
        org.exolab.castor.xml.FieldValidator fieldValidator = null;
        // -- initialize attribute descriptors

        // -- _package
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(
                java.lang.String.class,
                "_package",
                "package",
                org.exolab.castor.xml.NodeType.Attribute);
        desc.setImmutable(true);
        handler = (new org.exolab.castor.xml.XMLFieldHandler() {

                    @Override
                    public java.lang.Object getValue(final java.lang.Object object) throws IllegalStateException {
                        final Group target = (Group)object;
                        return target.getPackage();
                    }
                    @Override
                    public void setValue(final java.lang.Object object, final java.lang.Object value)
                            throws IllegalStateException, IllegalArgumentException {
                        try {
                            final Group target = (Group)object;
                            target.setPackage((java.lang.String)value);
                        } catch (java.lang.Exception ex) {
                            throw new IllegalStateException(ex.toString());
                        }
                    }
                    @Override
                    public java.lang.Object newInstance(final java.lang.Object parent) {
                        return null;
                    }
                });
        desc.setHandler(handler);
        desc.setRequired(true);
        addFieldDescriptor(desc);

        // -- validation code for: _package
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { // -- local scope
            final StringValidator typeValidator = new StringValidator();
            typeValidator.setWhiteSpace("preserve");
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        // -- _description
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(
                java.lang.String.class,
                "_description",
                "description",
                org.exolab.castor.xml.NodeType.Attribute);
        desc.setImmutable(true);
        handler = (new org.exolab.castor.xml.XMLFieldHandler() {

                    @Override
                    public java.lang.Object getValue(final java.lang.Object object) throws IllegalStateException {
                        final Group target = (Group)object;
                        return target.getDescription();
                    }
                    @Override
                    public void setValue(final java.lang.Object object, final java.lang.Object value)
                            throws IllegalStateException, IllegalArgumentException {
                        try {
                            final Group target = (Group)object;
                            target.setDescription((java.lang.String)value);
                        } catch (java.lang.Exception ex) {
                            throw new IllegalStateException(ex.toString());
                        }
                    }
                    @Override
                    public java.lang.Object newInstance(final java.lang.Object parent) {
                        return null;
                    }
                });
        desc.setHandler(handler);
        desc.setRequired(true);
        addFieldDescriptor(desc);

        // -- validation code for: _description
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { // -- local scope
            final StringValidator typeValidator = new StringValidator();
            typeValidator.setWhiteSpace("preserve");
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        // -- initialize element descriptors

        // -- _analyzerList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(
                de.cismet.cids.admin.analyze.castorGenerated.Analyzer.class,
                "_analyzerList",
                "analyzer",
                org.exolab.castor.xml.NodeType.Element);
        handler = (new org.exolab.castor.xml.XMLFieldHandler() {

                    @Override
                    public java.lang.Object getValue(final java.lang.Object object) throws IllegalStateException {
                        final Group target = (Group)object;
                        return target.getAnalyzer();
                    }
                    @Override
                    public void setValue(final java.lang.Object object, final java.lang.Object value)
                            throws IllegalStateException, IllegalArgumentException {
                        try {
                            final Group target = (Group)object;
                            target.addAnalyzer((de.cismet.cids.admin.analyze.castorGenerated.Analyzer)value);
                        } catch (java.lang.Exception ex) {
                            throw new IllegalStateException(ex.toString());
                        }
                    }
                    @Override
                    public java.lang.Object newInstance(final java.lang.Object parent) {
                        return new de.cismet.cids.admin.analyze.castorGenerated.Analyzer();
                    }
                });
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.cismet.de/cids");
        desc.setRequired(true);
        desc.setMultivalued(true);
        addFieldDescriptor(desc);

        // -- validation code for: _analyzerList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { // -- local scope
        }
        desc.setValidator(fieldValidator);
    }     // -- de.cismet.cids.admin.analyze.castorGenerated.GroupDescriptor()

    //~ Methods ----------------------------------------------------------------

    // -----------/
    // - Methods -/
    // -----------/

    /**
     * Method getAccessMode.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public org.exolab.castor.mapping.AccessMode getAccessMode() {
        return null;
    } // -- org.exolab.castor.mapping.AccessMode getAccessMode()

    /**
     * Method getExtends.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public org.exolab.castor.mapping.ClassDescriptor getExtends() {
        return null;
    } // -- org.exolab.castor.mapping.ClassDescriptor getExtends()

    /**
     * Method getIdentity.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public org.exolab.castor.mapping.FieldDescriptor getIdentity() {
        return identity;
    } // -- org.exolab.castor.mapping.FieldDescriptor getIdentity()

    /**
     * Method getJavaClass.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public java.lang.Class getJavaClass() {
        return de.cismet.cids.admin.analyze.castorGenerated.Group.class;
    } // -- java.lang.Class getJavaClass()

    /**
     * Method getNameSpacePrefix.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public java.lang.String getNameSpacePrefix() {
        return nsPrefix;
    } // -- java.lang.String getNameSpacePrefix()

    /**
     * Method getNameSpaceURI.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public java.lang.String getNameSpaceURI() {
        return nsURI;
    } // -- java.lang.String getNameSpaceURI()

    /**
     * Method getValidator.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public org.exolab.castor.xml.TypeValidator getValidator() {
        return this;
    } // -- org.exolab.castor.xml.TypeValidator getValidator()

    /**
     * Method getXMLName.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public java.lang.String getXMLName() {
        return xmlName;
    } // -- java.lang.String getXMLName()
}
