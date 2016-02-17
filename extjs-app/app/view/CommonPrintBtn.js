/*
 * File: app/view/CommonPrintBtn.js
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

Ext.define('foodpark.view.CommonPrintBtn', {
    extend: 'Ext.button.Button',
    alias: 'widget.commonprintbtn',

    requires: [
        'foodpark.view.CommonPrintBtnViewModel'
    ],

    viewModel: {
        type: 'commonprintbtn'
    },
    itemId: 'commonPrintBtn',
    style: 'font-family:Pictos;',
    glyph: 77,
    text: 'Print',

    initConfig: function(instanceConfig) {
        var me = this,
            config = {};
        me.processCommonPrintBtn(config);
        if (instanceConfig) {
            me.getConfigurator().merge(me, config, instanceConfig);
        }
        return me.callParent([config]);
    },

    processCommonPrintBtn: function(config) {
        config.text=Utilities.getMsg('common.printBtn.label');

        return config;
    }

});