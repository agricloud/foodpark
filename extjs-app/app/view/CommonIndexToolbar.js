/*
 * File: app/view/CommonIndexToolbar.js
 *
 * This file was generated by Sencha Architect version 3.2.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 5.1.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 5.1.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('foodpark.view.CommonIndexToolbar', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.commonindextoolbar',

    requires: [
        'foodpark.view.CommonIndexToolbarViewModel',
        'foodpark.view.CommonCreateBtn',
        'foodpark.view.CommonShowBtn',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'commonindextoolbar'
    },
    itemId: 'commonIndexToolbar',

    items: [
        {
            xtype: 'commoncreatebtn'
        },
        {
            xtype: 'commonshowbtn',
            disabled: true
        }
    ]

});