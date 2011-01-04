/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.common.gui.treetable;

/*
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following
 *   disclaimer in the documentation and/or other materials
 *   provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY
 * DAMAGES OR LIABILITIES SUFFERED BY LICENSEE AS A RESULT OF OR
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THIS SOFTWARE OR
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 */

import java.awt.*;
import java.awt.event.*;

import java.util.EventObject;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;

/**
 * This example shows how to create a simple JTreeTable component, by using a JTree as a renderer (and editor) for the
 * cells in a particular column in the JTable.
 *
 * @author   Philip Milne
 * @author   Scott Violet
 * @version  1.2 10/27/98
 */
public class JTreeTable extends JTable {

    //~ Instance fields --------------------------------------------------------

    /** A subclass of JTree. */
    protected TreeTableCellRenderer tree;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new JTreeTable object.
     *
     * @param  treeTableModel  DOCUMENT ME!
     */
    public JTreeTable(final TreeTableModel treeTableModel) {
        super();

        // Creates the tree. It will be used as a renderer and editor.
        tree = new TreeTableCellRenderer(treeTableModel);

        // Installs a tableModel representing the visible rows in the tree.
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));

        // Forces the JTable and JTree to share their row selection models.
        final ListToTreeSelectionModelWrapper selectionWrapper = new ListToTreeSelectionModelWrapper();
        tree.setSelectionModel(selectionWrapper);
        // tree.setRootVisible(false);
        setSelectionModel(selectionWrapper.getListSelectionModel());

        // Installs the tree editor renderer and editor.
        setDefaultRenderer(TreeTableModel.class, tree);
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());

        // No grid.
        setShowGrid(false);

        // No intercell spacing
        setIntercellSpacing(new Dimension(0, 0));

        // And update the height of the trees row to match that of
        // the table.
        if (tree.getRowHeight() < 1) {
            // Metal looks better like this.
            setRowHeight(20);
        }
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Overridden to message super and forward the method to the tree. Since the tree is not actually in the component
     * hierarchy it will never receive this unless we forward it in this manner.
     */
    @Override
    public void updateUI() {
        super.updateUI();
        if (tree != null) {
            tree.updateUI();
            // Do this so that the editor is referencing the current renderer
            // from the tree. The renderer can potentially change each time
            // laf changes.
            setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());
        }
        // Use the tree's default foreground and background colors in the
        // table.
        LookAndFeel.installColorsAndFont(this, "Tree.background",
            "Tree.foreground", "Tree.font");
    }

    /**
     * Workaround for BasicTableUI anomaly. Make sure the UI never tries to resize the editor. The UI currently uses
     * different techniques to paint the renderers and editors; overriding setBounds() below is not the right thing to
     * do for an editor. Returning -1 for the editing row in this case, ensures the editor is never painted.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public int getEditingRow() {
        return (getColumnClass(editingColumn) == TreeTableModel.class) ? -1 : editingRow;
    }

    /**
     * Returns the actual row that is editing as <code>getEditingRow</code> will always return -1.
     *
     * @return  DOCUMENT ME!
     */
    private int realEditingRow() {
        return editingRow;
    }

    /**
     * This is overridden to invoke super's implementation, and then, if the receiver is editing a Tree column, the
     * editor's bounds is reset. The reason we have to do this is because JTable doesn't think the table is being
     * edited, as <code>getEditingRow</code> returns -1, and therefore doesn't automatically resize the editor for us.
     *
     * @param  resizingColumn  DOCUMENT ME!
     */
    @Override
    public void sizeColumnsToFit(final int resizingColumn) {
        super.sizeColumnsToFit(resizingColumn);
        if ((getEditingColumn() != -1) && (getColumnClass(editingColumn)
                        == TreeTableModel.class)) {
            final Rectangle cellRect = getCellRect(realEditingRow(),
                    getEditingColumn(), false);
            final Component component = getEditorComponent();
            component.setBounds(cellRect);
            component.validate();
        }
    }

    /**
     * Overridden to pass the new rowHeight to the tree.
     *
     * @param  rowHeight  DOCUMENT ME!
     */
    @Override
    public void setRowHeight(final int rowHeight) {
        super.setRowHeight(rowHeight);
        if ((tree != null) && (tree.getRowHeight() != rowHeight)) {
            tree.setRowHeight(getRowHeight());
        }
    }

    /**
     * Returns the tree that is being shared between the model.
     *
     * @return  DOCUMENT ME!
     */
    public JTree getTree() {
        return tree;
    }

    /**
     * Overridden to invoke repaint for the particular location if the column contains the tree. This is done as the
     * tree editor does not fill the bounds of the cell, we need the renderer to paint the tree in the background, and
     * then draw the editor over it.
     *
     * @param   row     DOCUMENT ME!
     * @param   column  DOCUMENT ME!
     * @param   e       DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public boolean editCellAt(final int row, final int column, final EventObject e) {
        final boolean retValue = super.editCellAt(row, column, e);
        if (retValue && (getColumnClass(column) == TreeTableModel.class)) {
            repaint(getCellRect(row, column, false));
        }
        return retValue;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public TreeTableModel getTreeTableModel() {
        return ((TreeTableModelAdapter)this.getModel()).getTreeTableModel();
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * A TreeCellRenderer that displays a JTree.
     *
     * @version  $Revision$, $Date$
     */
    public class TreeTableCellRenderer extends JTree implements TableCellRenderer {

        //~ Instance fields ----------------------------------------------------

        /** Last table/tree row asked to renderer. */
        protected int visibleRow;
        /** Border to draw around the tree, if this is non-null, it will be painted. */
        protected Border highlightBorder;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new TreeTableCellRenderer object.
         *
         * @param  model  DOCUMENT ME!
         */
        public TreeTableCellRenderer(final TreeModel model) {
            super(model);
            // this.setShowsRootHandles(true);
        }

        //~ Methods ------------------------------------------------------------

        /**
         * updateUI is overridden to set the colors of the Tree's renderer to match that of the table.
         */
        @Override
        public void updateUI() {
            super.updateUI();
            // Make the tree's cell renderer use the table's cell selection
            // colors.
            final TreeCellRenderer tcr = getCellRenderer();
            if (tcr instanceof DefaultTreeCellRenderer) {
                final DefaultTreeCellRenderer dtcr = ((DefaultTreeCellRenderer)tcr);
                // For 1.1 uncomment this, 1.2 has a bug that will cause an
                // exception to be thrown if the border selection color is
                // null.
                // dtcr.setBorderSelectionColor(null);
                dtcr.setTextSelectionColor(UIManager.getColor("Table.selectionForeground"));
                dtcr.setBackgroundSelectionColor(UIManager.getColor("Table.selectionBackground"));
            }
        }

        /**
         * Sets the row height of the tree, and forwards the row height to the table.
         *
         * @param  rowHeight  DOCUMENT ME!
         */
        @Override
        public void setRowHeight(final int rowHeight) {
            if (rowHeight > 0) {
                super.setRowHeight(rowHeight);
                if ((JTreeTable.this != null)
                            && (JTreeTable.this.getRowHeight() != rowHeight)) {
                    JTreeTable.this.setRowHeight(getRowHeight());
                }
            }
        }

        /**
         * This is overridden to set the height to match that of the JTable.
         *
         * @param  x  DOCUMENT ME!
         * @param  y  DOCUMENT ME!
         * @param  w  DOCUMENT ME!
         * @param  h  DOCUMENT ME!
         */
        @Override
        public void setBounds(final int x, final int y, final int w, final int h) {
            super.setBounds(x, 0, w, JTreeTable.this.getHeight());
        }

        /**
         * Sublcassed to translate the graphics such that the last visible row will be drawn at 0,0.
         *
         * @param  g  DOCUMENT ME!
         */
        @Override
        public void paint(final Graphics g) {
            g.translate(0, -visibleRow * getRowHeight());
            super.paint(g);
            // Draw the Table border if we have focus.
            if (highlightBorder != null) {
                highlightBorder.paintBorder(this, g, 0, visibleRow
                            * getRowHeight(), getWidth(),
                    getRowHeight());
            }
        }

        /**
         * TreeCellRenderer method. Overridden to update the visible row.
         *
         * @param   table       DOCUMENT ME!
         * @param   value       DOCUMENT ME!
         * @param   isSelected  DOCUMENT ME!
         * @param   hasFocus    DOCUMENT ME!
         * @param   row         DOCUMENT ME!
         * @param   column      DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         */
        @Override
        public Component getTableCellRendererComponent(final JTable table,
                final Object value,
                final boolean isSelected,
                final boolean hasFocus,
                final int row,
                final int column) {
            Color background;
            Color foreground;

            if (isSelected) {
                background = table.getSelectionBackground();
                foreground = table.getSelectionForeground();
            } else {
                background = table.getBackground();
                foreground = table.getForeground();
            }
            highlightBorder = null;
            if ((realEditingRow() == row) && (getEditingColumn() == column)) {
                background = UIManager.getColor("Table.focusCellBackground");
                foreground = UIManager.getColor("Table.focusCellForeground");
            } else if (hasFocus) {
                highlightBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
                if (isCellEditable(row, column)) {
                    background = UIManager.getColor("Table.focusCellBackground");
                    foreground = UIManager.getColor("Table.focusCellForeground");
                }
            }

            visibleRow = row;
            setBackground(background);

            final TreeCellRenderer tcr = getCellRenderer();
            if (tcr instanceof DefaultTreeCellRenderer) {
                final DefaultTreeCellRenderer dtcr = ((DefaultTreeCellRenderer)tcr);
                if (isSelected) {
                    dtcr.setTextSelectionColor(foreground);
                    dtcr.setBackgroundSelectionColor(background);
                } else {
                    dtcr.setTextNonSelectionColor(foreground);
                    dtcr.setBackgroundNonSelectionColor(background);
                }
            }
            return this;
        }
    }

    /**
     * An editor that can be used to edit the tree column. This extends DefaultCellEditor and uses a JTextField
     * (actually, TreeTableTextField) to perform the actual editing.
     *
     * <p>To support editing of the tree column we can not make the tree editable. The reason this doesn't work is that
     * you can not use the same component for editing and renderering. The table may have the need to paint cells, while
     * a cell is being edited. If the same component were used for the rendering and editing the component would be
     * moved around, and the contents would change. When editing, this is undesirable, the contents of the text field
     * must stay the same, including the caret blinking, and selections persisting. For this reason the editing is done
     * via a TableCellEditor.</p>
     *
     * <p>Another interesting thing to be aware of is how tree positions its render and editor. The render/editor is
     * responsible for drawing the icon indicating the type of node (leaf, branch...). The tree is responsible for
     * drawing any other indicators, perhaps an additional +/- sign, or lines connecting the various nodes. So, the
     * renderer is positioned based on depth. On the other hand, table always makes its editor fill the contents of the
     * cell. To get the allusion that the table cell editor is part of the tree, we don't want the table cell editor to
     * fill the cell bounds. We want it to be placed in the same manner as tree places it editor, and have table message
     * the tree to paint any decorations the tree wants. Then, we would only have to worry about the editing part. The
     * approach taken here is to determine where tree would place the editor, and to override the <code>reshape</code>
     * method in the JTextField component to nudge the textfield to the location tree would place it. Since JTreeTable
     * will paint the tree behind the editor everything should just work. So, that is what we are doing here.
     * Determining of the icon position will only work if the TreeCellRenderer is an instance of
     * DefaultTreeCellRenderer. If you need custom TreeCellRenderers, that don't descend from DefaultTreeCellRenderer,
     * and you want to support editing in JTreeTable, you will have to do something similiar.</p>
     *
     * @version  $Revision$, $Date$
     */
    public class TreeTableCellEditor extends DefaultCellEditor {

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new TreeTableCellEditor object.
         */
        public TreeTableCellEditor() {
            super(new TreeTableTextField());
        }

        //~ Methods ------------------------------------------------------------

        /**
         * Overridden to determine an offset that tree would place the editor at. The offset is determined from the
         * <code>getRowBounds</code> JTree method, and additionally from the icon DefaultTreeCellRenderer will use.
         *
         * <p>The offset is then set on the TreeTableTextField component created in the constructor, and returned.</p>
         *
         * @param   table       DOCUMENT ME!
         * @param   value       DOCUMENT ME!
         * @param   isSelected  DOCUMENT ME!
         * @param   r           DOCUMENT ME!
         * @param   c           DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         */
        @Override
        public Component getTableCellEditorComponent(final JTable table,
                final Object value,
                final boolean isSelected,
                final int r,
                final int c) {
            final Component component = super.getTableCellEditorComponent(table, value, isSelected, r, c);
            final JTree t = getTree();
            final boolean rv = t.isRootVisible();
            final int offsetRow = rv ? r : (r - 1);
            final Rectangle bounds = t.getRowBounds(offsetRow);
            int offset = bounds.x;
            final TreeCellRenderer tcr = t.getCellRenderer();
            if (tcr instanceof DefaultTreeCellRenderer) {
                final Object node = t.getPathForRow(offsetRow).getLastPathComponent();
                Icon icon;
                if (t.getModel().isLeaf(node)) {
                    icon = ((DefaultTreeCellRenderer)tcr).getLeafIcon();
                } else if (tree.isExpanded(offsetRow)) {
                    icon = ((DefaultTreeCellRenderer)tcr).getOpenIcon();
                } else {
                    icon = ((DefaultTreeCellRenderer)tcr).getClosedIcon();
                }
                if (icon != null) {
                    offset += ((DefaultTreeCellRenderer)tcr).getIconTextGap()
                                + icon.getIconWidth();
                }
            }
            ((TreeTableTextField)getComponent()).offset = offset;
            return component;
        }

        /**
         * This is overridden to forward the event to the tree. This will return true if the click count >= 3, or the
         * event is null.
         *
         * @param   e  DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         */
        @Override
        public boolean isCellEditable(final EventObject e) {
            if (e instanceof MouseEvent) {
                final MouseEvent me = (MouseEvent)e;
                // If the modifiers are not 0 (or the left mouse button),
                // tree may try and toggle the selection, and table
                // will then try and toggle, resulting in the
                // selection remaining the same. To avoid this, we
                // only dispatch when the modifiers are 0 (or the left mouse
                // button).
                if ((me.getModifiers() == 0)
                            || (me.getModifiers() == InputEvent.BUTTON1_MASK)) {
                    for (int counter = getColumnCount() - 1; counter >= 0; counter--) {
                        if (getColumnClass(counter) == TreeTableModel.class) {
                            final MouseEvent newME = new MouseEvent(
                                    JTreeTable.this.tree,
                                    me.getID(),
                                    me.getWhen(),
                                    me.getModifiers(),
                                    me.getX()
                                            - getCellRect(0, counter, true).x,
                                    me.getY(),
                                    me.getClickCount(),
                                    me.isPopupTrigger());
                            JTreeTable.this.tree.dispatchEvent(newME);
                            break;
                        }
                    }
                }
                /*if (me.getClickCount() >= 3)
                 * { return true;}*/
                return false;
            }
            if (e == null) {
                return true;
            }
            return false;
        }
    }

    /**
     * Component used by TreeTableCellEditor. The only thing this does is to override the <code>reshape</code> method,
     * and to ALWAYS make the x location be <code>offset</code>.
     *
     * @version  $Revision$, $Date$
     */
    static class TreeTableTextField extends JTextField {

        //~ Instance fields ----------------------------------------------------

        public int offset;

        //~ Methods ------------------------------------------------------------

        @Override
        public void reshape(final int x, final int y, final int w, final int h) {
            final int newX = Math.max(x, offset);
            super.reshape(newX, y, w - (newX - x), h);
        }
    }

    /**
     * ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel to listen for changes in the ListSelectionModel
     * it maintains. Once a change in the ListSelectionModel happens, the paths are updated in the
     * DefaultTreeSelectionModel.
     *
     * @version  $Revision$, $Date$
     */
    class ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel {

        //~ Instance fields ----------------------------------------------------

        /** Set to true when we are updating the ListSelectionModel. */
        protected boolean updatingListSelectionModel;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new ListToTreeSelectionModelWrapper object.
         */
        public ListToTreeSelectionModelWrapper() {
            super();
            getListSelectionModel().addListSelectionListener(createListSelectionListener());
        }

        //~ Methods ------------------------------------------------------------

        /**
         * Returns the list selection model. ListToTreeSelectionModelWrapper listens for changes to this model and
         * updates the selected paths accordingly.
         *
         * @return  DOCUMENT ME!
         */
        ListSelectionModel getListSelectionModel() {
            return listSelectionModel;
        }

        /**
         * This is overridden to set <code>updatingListSelectionModel</code> and message super. This is the only place
         * DefaultTreeSelectionModel alters the ListSelectionModel.
         */
        @Override
        public void resetRowSelection() {
            if (!updatingListSelectionModel) {
                updatingListSelectionModel = true;
                try {
                    super.resetRowSelection();
                } finally {
                    updatingListSelectionModel = false;
                }
            }
            // Notice how we don't message super if
            // updatingListSelectionModel is true. If
            // updatingListSelectionModel is true, it implies the
            // ListSelectionModel has already been updated and the
            // paths are the only thing that needs to be updated.
        }

        /**
         * Creates and returns an instance of ListSelectionHandler.
         *
         * @return  DOCUMENT ME!
         */
        protected ListSelectionListener createListSelectionListener() {
            return new ListSelectionHandler();
        }

        /**
         * If <code>updatingListSelectionModel</code> is false, this will reset the selected paths from the selected
         * rows in the list selection model.
         */
        protected void updateSelectedPathsFromSelectedRows() {
            if (!updatingListSelectionModel) {
                updatingListSelectionModel = true;
                try {
                    // This is way expensive, ListSelectionModel needs an
                    // enumerator for iterating.
                    final int min = listSelectionModel.getMinSelectionIndex();
                    final int max = listSelectionModel.getMaxSelectionIndex();

                    clearSelection();
                    if ((min != -1) && (max != -1)) {
                        for (int counter = min; counter <= max; counter++) {
                            if (listSelectionModel.isSelectedIndex(counter)) {
                                final TreePath selPath = tree.getPathForRow(counter);

                                if (selPath != null) {
                                    addSelectionPath(selPath);
                                }
                            }
                        }
                    }
                } finally {
                    updatingListSelectionModel = false;
                }
            }
        }

        //~ Inner Classes ------------------------------------------------------

        /**
         * Class responsible for calling updateSelectedPathsFromSelectedRows when the selection of the list changse.
         *
         * @version  $Revision$, $Date$
         */
        class ListSelectionHandler implements ListSelectionListener {

            //~ Methods --------------------------------------------------------

            @Override
            public void valueChanged(final ListSelectionEvent e) {
                updateSelectedPathsFromSelectedRows();
            }
        }
    }
}
