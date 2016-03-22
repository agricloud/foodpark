/*
 * File: app/view/CommonCancelBtn.js
 *
 * This file was generated by Sencha Architect version 3.5.0.
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

Ext.define('foodpark.view.CommonCancelBtn', {
    extend: 'Ext.button.Button',
    alias: 'widget.commoncancelbtn',

    requires: [
        'foodpark.view.CommonCancelBtnViewModel'
    ],

    viewModel: {
        type: 'commoncancelbtn'
    },
    itemId: 'commonCancelBtn',
    style: 'font-family:Pictos;',
    glyph: 115,
    text: 'Cancel',

    initConfig: function(instanceConfig) {
        var me = this,
            config = {};
        me.processCommonCancelBtn(config);
        if (instanceConfig) {
            me.getConfigurator().merge(me, config, instanceConfig);
        }
        return me.callParent([config]);
    },

    processCommonCancelBtn: function(config) {
        config.text=Utilities.getMsg('common.cancelBtn.label');

        return config;
    }

});