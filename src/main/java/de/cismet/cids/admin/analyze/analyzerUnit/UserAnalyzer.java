/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
/*
 * ExampleAnalyzer.java
 *
 * Created on 5. Februar 2004, 14:32
 */
package de.cismet.cids.admin.analyze.analyzerUnit;
import java.sql.*;

import de.cismet.cids.admin.analyze.*;
/**
 * DOCUMENT ME!
 *
 * @author   hell
 * @version  $Revision$, $Date$
 */
public class UserAnalyzer extends CidsAnalyzer {

    //~ Methods ----------------------------------------------------------------

    @Override
    public AnalyseResult analyse(final Connection metaConn) {
        final AnalyseResult r = new AnalyseResult();
        r.appendHeading("User");
        r.appendSubHeading("Benutzer und ihre Benutzergruppen");
        r.appendText("Alle Benutzer diese Metadatenbank.");
        try {
            final Statement s = metaConn.createStatement();
            final ResultSet rs = s.executeQuery(
                    "select login_name as \"Login\",g.name as \"Gruppenname\", domainname as \"Domainname\" from cs_usr u ,cs_ug g,cs_ug_membership m where m.ug_id=g.id and u.id=m.usr_id");
            r.appendResultSet(rs);
        } catch (Exception e) {
            r.appendText("Statement konnte nicht abgesetzt werden (" + e.getMessage() + ")");
        }

        return r;
    }
}
