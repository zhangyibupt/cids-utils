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
 * $Id: Analyzer.java,v 1.1.1.1 2009-08-20 12:23:31 spuhl Exp $
 */
package de.cismet.cids.admin.analyze.castorGenerated;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import org.xml.sax.ContentHandler;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

/**
 * Class Analyzer.
 *
 * @version  $Revision: 1.1.1.1 $ $Date: 2009-08-20 12:23:31 $
 */
public class Analyzer implements java.io.Serializable {

    //~ Instance fields --------------------------------------------------------

    // --------------------------/
    // - Class/Member Variables -/
    // --------------------------/

    /** internal content storage. */
    private java.lang.String _content = "";

    /** Field _classname. */
    private java.lang.String _classname;

    //~ Constructors -----------------------------------------------------------

    /**
     * ----------------/ - Constructors -/ ----------------/
     */
    public Analyzer() {
        super();
        setContent("");
    } // -- de.cismet.cids.admin.analyze.castorGenerated.Analyzer()

    //~ Methods ----------------------------------------------------------------

    // -----------/
    // - Methods -/
    // -----------/

    /**
     * Returns the value of field 'classname'.
     *
     * @return  the value of field 'classname'.
     */
    public java.lang.String getClassname() {
        return this._classname;
    } // -- java.lang.String getClassname()

    /**
     * Returns the value of field 'content'. The field 'content' has the following description: internal content storage
     *
     * @return  the value of field 'content'.
     */
    public java.lang.String getContent() {
        return this._content;
    } // -- java.lang.String getContent()

    /**
     * Method isValid.
     *
     * @return  DOCUMENT ME!
     */
    public boolean isValid() {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } // -- boolean isValid()

    /**
     * Method marshal.
     *
     * @param   out  DOCUMENT ME!
     *
     * @throws  org.exolab.castor.xml.MarshalException     DOCUMENT ME!
     * @throws  org.exolab.castor.xml.ValidationException  DOCUMENT ME!
     */
    public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException,
        org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    } // -- void marshal(java.io.Writer)

    /**
     * Method marshal.
     *
     * @param   handler  DOCUMENT ME!
     *
     * @throws  java.io.IOException                        DOCUMENT ME!
     * @throws  org.exolab.castor.xml.MarshalException     DOCUMENT ME!
     * @throws  org.exolab.castor.xml.ValidationException  DOCUMENT ME!
     */
    public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException,
        org.exolab.castor.xml.MarshalException,
        org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    } // -- void marshal(org.xml.sax.ContentHandler)

    /**
     * Sets the value of field 'classname'.
     *
     * @param  classname  the value of field 'classname'.
     */
    public void setClassname(final java.lang.String classname) {
        this._classname = classname;
    } // -- void setClassname(java.lang.String)

    /**
     * Sets the value of field 'content'. The field 'content' has the following description: internal content storage
     *
     * @param  content  the value of field 'content'.
     */
    public void setContent(final java.lang.String content) {
        this._content = content;
    } // -- void setContent(java.lang.String)

    /**
     * Method unmarshal.
     *
     * @param   reader  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  org.exolab.castor.xml.MarshalException     DOCUMENT ME!
     * @throws  org.exolab.castor.xml.ValidationException  DOCUMENT ME!
     */
    public static de.cismet.cids.admin.analyze.castorGenerated.Analyzer unmarshal(final java.io.Reader reader)
            throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.cismet.cids.admin.analyze.castorGenerated.Analyzer)Unmarshaller.unmarshal(
                de.cismet.cids.admin.analyze.castorGenerated.Analyzer.class,
                reader);
    } // -- de.cismet.cids.admin.analyze.castorGenerated.Analyzer unmarshal(java.io.Reader)

    /**
     * Method validate.
     *
     * @throws  org.exolab.castor.xml.ValidationException  DOCUMENT ME!
     */
    public void validate() throws org.exolab.castor.xml.ValidationException {
        final org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } // -- void validate()
}
