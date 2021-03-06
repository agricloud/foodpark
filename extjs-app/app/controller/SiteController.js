/*
 * File: app/controller/SiteController.js
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

Ext.define('foodpark.controller.SiteController', {
    extend: 'Ext.app.Controller',

    mixins: {
        commonController: 'foodpark.controller.CommonController'
    },

    models: [
        'Site'
    ],
    stores: [
        'SiteStore'
    ],
    views: [
        'SiteView'
    ],

    refs: {
        mainGrid: 'siteview #grid',
        mainForm: 'siteview #form'
    },

    init: function(application) {
        this.control({
            'siteview #index commonindextoolbar commonshowbtn': {
                click: this.doShow
            },
            'siteview #show commonshowtoolbar commonsavebtn': {
                click: this.doSave
            },
            'siteview #show commonshowtoolbar commoncancelbtn': {
                click: this.doCancel
            },
            'siteview #grid':{
                select: this.enableShowBtn,
                deselect: this.disableShowBtn,
                itemdblclick: this.doShow
            }
        });

        this.domainName = 'site';
    }

});
