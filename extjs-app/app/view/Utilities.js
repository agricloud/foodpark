/*
 * File: app/view/Utilities.js
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

Ext.define('foodpark.view.Utilities', {
    extend: 'Ext.app.Controller',

    alternateClassName: [
        'Utilities'
    ],
    singleton: true,

    respondFailure: function(action, config) {
        Ext.MessageBox.alert('Failure',action.result.message);


        if(action.url.indexOf('create')!=-1){
            config.toolbar().down('button[itemId=commonSaveBtn]').setDisabled(false);
        }else {
            config.toolbar().down('button[itemId=commonDeleteBtn]').setDisabled(false);
            config.toolbar().down('button[itemId=commonUpdateBtn]').setDisabled(false);
        }
    },

    respondSuccess: function(action, config) {
        Ext.MessageBox.alert('Success',action.result.message);

        console.log(config.grid());
        config.grid().getStore().reload();
        config.form().getForm().reset(true);
    },

    validityChange: function(basic, valid, config) {
        config.form().getForm().updateRecord();

        if(basic.getRecord()!=null && basic.getRecord().getData().id!=null){
            if(valid){
                config.toolbar().down('button[itemId=commonUpdateBtn]').setDisabled(false);
            }
            else{
                config.toolbar().down('button[itemId=commonUpdateBtn]').setDisabled(true);
            }
        }
        else{
            if(valid){
                config.toolbar().down('button[itemId=commonSaveBtn]').setDisabled(false);
            }
            else{
                config.toolbar().down('button[itemId=commonSaveBtn]').setDisabled(true);
            }
        }
    },

    createFiltersFeature: function(config, component) {
        if(config){
            for (var i=0; i<config.columns.length; i++) {
                if(config.columns[i].xtype === 'numbercolumn'){
                    config.columns[i].type = 'numeric';
                }
                else if(config.columns[i].xtype === 'datecolumn'){
                    config.columns[i].type = 'date';
                }
                else if(config.columns[i].xtype === 'booleancolumn'){
                    config.columns[i].type = 'boolean';
                }

                config.columns[i].filter = {
                    type: config.columns[i].type
                };
            }

            /* extjs 4.2
            config.features = [{
                ftype: 'filters',
                encode: true,
                filters: config.columns
            }];
            */

            return config;
        }
        if(component){
            var columns = component.getColumns();
            for (var i=0; i<columns.length; i++) {
                if(columns[i].getXType() === 'numbercolumn'){
                    columns[i].type = 'numeric';
                }
                else if(columns[i].getXType() === 'datecolumn'){
                    columns[i].type = 'date';
                }
                else if(columns[i].getXType() === 'booleancolumn'){
                    columns[i].type = 'boolean';
                }

                columns[i].filter = {
                    type: columns[i].type
                };
            }
        }
    },

    logout: function() {
        Ext.MessageBox.confirm('logout', Utilities.getMsg('default.message.logout'), function(btn){
            if(btn === 'yes'){

                Utilities.doLogout();

            }
        });
    },

    processConfigBundle: function(config, prefix) {
        if (!prefix) {
            console.warn("processConfigBundle require a prefix argument");
        }
        /*
        if(config instanceof Ext.form.FieldContainer || config.xtype=='fieldcontainer' ){
        var key = prefix+'.label';

        if (!this.getMsg(key).match(".undefined")) {
        config.fieldLabel = this.getMsg(key);
        }else console.log(this.getMsg(key));
        }
        */
        if ((config instanceof Ext.form.Panel || config.xtype=='form') && config.items) {
            for (var i=0; i<config.items.length; i++) {
                var target = config.items[i];

                if (target.fieldLabel && target.name) {
                    var key = prefix+'.'+target.name+'.label';
                    // Check lang def exists
                    if (!this.getMsg(key).match(".undefined")) {
                        target.fieldLabel = this.getMsg(key);
                    }else console.log(this.getMsg(key));
                }
                if(target.items){
                    for(var j=0; j<target.items.length; j++){
                        var innerTarget=target.items[j];
                        if(innerTarget.fieldLabel){
                            key = prefix+'.'+innerTarget.name+'.label';
                            // Check lang def exists
                            if (!this.getMsg(key).match(".undefined")) {
                                innerTarget.fieldLabel = this.getMsg(key);
                            }else console.log(this.getMsg(key));
                        }
                    }
                }
            }
        }

        if ((config instanceof Ext.grid.Panel || config.xtype=='gridpanel' || config.xtype=='treepanel' || config instanceof Ext.tree.Panel) && config.columns) {
            if(config.title != null)
            config.title = this.getMsg(prefix+'.label');
            for (var i=0; i<config.columns.length; i++) {
                var target = config.columns[i];
                if (target.text) {
                    var key = prefix+'.'+target.dataIndex+'.label';
                    // Check lang def exists
                    if (!this.getMsg(key).match(".undefined")) {
                        target.text  = this.getMsg(key);
                    }else console.log(this.getMsg(key));
                }
            }
        }

        return config;
    },

    processI18nBundle: function(component, prefix) {
        if (!prefix) {
            console.warn("processI18nBundle require a prefix argument");
        }

        if ((component instanceof Ext.form.Panel || component.getXType()=='form') && component.items) {
            for (var i=0; i<component.items.items.length; i++) {
                var target = component.items.items[i];
                if (target.fieldLabel && target.name) {
                    var key = prefix+'.'+target.name+'.label';
                    // Check lang def exists
                    if (!this.getMsg(key).match(".undefined")) {
                        target.fieldLabel = this.getMsg(key);
                    }else console.log(this.getMsg(key));
                }
                if(target.items){
                    for(var j=0; j<target.items.items.length; j++){
                        var innerTarget=target.items.items[j];
                        if(innerTarget.fieldLabel && innerTarget.name){
                            key = prefix+'.'+innerTarget.name+'.label';
                            // Check lang def exists
                            if (!this.getMsg(key).match(".undefined")) {
                                innerTarget.setFieldLabel(this.getMsg(key));
                            }else console.log(this.getMsg(key));
                        }
                    }
                }
            }
        }

        if ((component instanceof Ext.grid.Panel || component.getXType()=='gridpanel' || component.getXType()=='treepanel' || component instanceof Ext.tree.Panel) && component.getColumns()) {
            if(component.getTitle() != null)
                component.setTitle(this.getMsg(prefix+'.label'));
            var columns = component.getColumns();
            for (var i=0; i<columns.length; i++) {
                var target = columns[i];
                var key = prefix+'.'+target.dataIndex+'.label';
                // Check lang def exists
                if (!this.getMsg(key).match(".undefined")) {
                    target.setText(this.getMsg(key));
                }else console.log(this.getMsg(key));
            }
        }
    },

    getMsg: function(lang) {
        lang=Utilities.getSysConfig("i18nType")+"."+lang;
        return foodpark.getApplication().bundle.getMsg(lang);
    },

    readSysConfig: function(callback) {
        var that = this;

        Ext.Ajax.request({
            url: '/api/readSysConfig',
            async: false,
            params: {
                senchaEnv: "extjs"
            },
            success: function(response, opts) {
                that.sysConfig = Ext.decode(response.responseText);
                if(callback)
                    callback();
            },
            failure: function(response, opts) {
                Ext.MessageBox.alert('Failure', "無法讀取系統參數");
            }
        });
    },

    getSysConfig: function(name) {
        return this.sysConfig[name];
    },

    checkSession: function() {
        var that=this;

        this.checkSessionTask = {
            run: function(){
                that.readSysConfig(function(){

                    var username=that.getSysConfig("username");
                    if(username){
                        console.log(username+"連線ing..."+new Date());
                    }
                    else{
                        console.log("連線逾時已登出"+new Date());
                        that.doLogout();
                        Ext.MessageBox.alert('登出提示', '連線逾時已登出');
                    }

                });

            },
            interval: 1800000//30mins
        };

        Ext.TaskManager.start(this.checkSessionTask);
    },

    doLogout: function() {
        Ext.Ajax.request({
            url: '/j_spring_security_logout',
            success: function(response){
                //var text = response.responseText;
                var mainVP = Ext.getCmp('mainVP');
                mainVP.removeAll();
                mainVP.add({
                    xtype: 'logincontainer'
                });
            }
        });

        this.stopCheckSession();
    },

    stopCheckSession: function() {
        Ext.TaskManager.stop(this.checkSessionTask);
    },

    comboReload: function(combo, id, value) {
        combo.getStore().load({
            params: {'nameLike': value},
            callback: function(records, operation, success) {
                combo.setValue(id);
            }
        });
    }

});