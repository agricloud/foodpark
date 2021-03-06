/*
 * File: app.js
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

// @require @packageOverrides
Ext.Loader.setConfig({
    paths: {
        'Ext.ux': 'extjs/examples/ux'
    }
});


Ext.application({

    requires: [
        'Ext.Loader',
        'foodpark.view.Utilities',
        'foodpark.controller.CommonController',
        'Ext.i18n.Bundle'
    ],
    bundle: {
        bundle: 'Application-agri',
        lang: 'zh-TW',
        path: '../i18n',
        noCache: true//,format: 'json'
    },
    controllers: [
        'SiteController',
        'LoginController'
    ],
    name: 'foodpark',

    launch: function() {
        Ext.create('foodpark.view.MainViewport');
        Ext.form.DateField.prototype.altFormats = 'm/d/Y|n/j/Y|n/j/y|m/j/y|n/d/y|m/j/Y|n/d/Y|m-d-y|m-d-Y|m/d|m-d|md|mdy|mdY|d|Y-m-d|n-j|n/j|Y-m-d\\TH:i:s\\Z';
        Ext.form.DateField.prototype.submitFormat = 'Y-m-d\\TH:i:s';

        //Ext.form.DateField.prototype.submitFormat = 'Y-m-d H:i:s \\z';

        //為在初始化完成前讀取sysConfig.i18nType
        //改由mainViewport.processConfig呼叫readSysConfig

        //var afterConfigRead = function(){

        //未登出自動轉入系統畫面
        if(Utilities.getSysConfig("username")){
            //LoginController loginSuccess
            var mainVP = Ext.getCmp('mainVP');
            mainVP.removeAll();
            mainVP.add({xtype: 'maincontainer'});

            //Ext.get('username').update(Utilities.getSysConfig("username"), false);
            Ext.get('username').update(Utilities.getSysConfig("fullName")+"("+Utilities.getSysConfig("username")+")", false);

            //計時檢查連線
            Utilities.checkSession();
        }
        else{
            if(Utilities.getSysConfig("environment")== "development"){
                var login = Ext.getCmp("login");
                login.down("textfield[name=j_username]").setValue("admin");
                login.down("textfield[name=j_password]").setValue("admin");
            }
            if(Utilities.getSysConfig("environment")== "production"){
                window.onbeforeunload = function(e) {
                    return Utilities.getMsg('default.message.leave');
                };
            }


        }
    }

});
