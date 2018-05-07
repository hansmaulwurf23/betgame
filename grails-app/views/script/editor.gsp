<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main">
    <title>Editor</title>

    <asset:javascript src="cmeditor.js" />
    <asset:stylesheet src="cmeditor.css" />

    <asset:javascript src="historyviewer.js"/>
    <asset:javascript src="jqueryui-editable.js"/>

    <style type="text/css">
    div.cmeditor.cmeditor-fullscreen {
        z-index: 650;
    }
    .CodeMirror-fullscreen {
        z-index: 900;
    }
    .ui-front {
        z-index: 1000;
    }
    .CodeMirror-hints {
        z-index: 1100;
    }
    .pp-content ul.cmeditor-menubar {
        margin:0px;
    }
    </style>

</head>
<body class="pp-completeWidth">
<div class="body">

    <cmeditor:tabs name="script"
                   options="[defaultContent:'process { }', defaultMode:'text/x-groovy']"
                   ajax="[listURL:'listFiles', deleteURL:'deleteFile', updateURL:'saveFile', getURL:'loadFile']"
                   mapping="[idField:'name']">


        <fieldset>
            <legend>Settings</legend>

            <div class="fieldcontain">
                <label for="dsl"> <g:message code="script.dsl.label" default="DSL" /></label>
                <g:textField name="dsl" class="cmeditor-field" />
            </div>
            <div class="fieldcontain">
                <label for="status"> <g:message code="script.status.label" default="Status" /></label>
                <g:textField name="status" class="cmeditor-field" />
            </div>
            <div class="fieldcontain">
                <label for="folder"> <g:message code="script.folder.label" default="Folder" /></label>
                <g:textField name="folder" class="cmeditor-field" />
            </div>
            <div class="fieldcontain">
                <label for="mode"> <g:message code="script.mode.label" default="Mode" /></label>
                <g:textField name="mode" class="cmeditor-field" />
            </div>
            <div class="fieldcontain">
                <label for="mode"> <g:message code="script.version.label" default="Version" /></label>
                <g:textField name="version" class="cmeditor-field" />
            </div>
            <div class="fieldcontain">
                <label for="author"> <g:message code="script.author.label" default="Author" /></label>
                <g:textField name="author" class="cmeditor-field" />
            </div>

        </fieldset>

    </cmeditor:tabs>


</div>

<script type="text/javascript">

    function sleep (time) {
        return new Promise((resolve) => setTimeout(resolve, time));
    }

    $(document).ready(
        function() {

            var editor = CMEditor.getInstance("script");
            var actions = editor.menu.addRootMenuEntry("Actions");
            editor.menu.addSubMenuEntry(actions, "Run", function(event) {
                ajax_script_do('run', {});
            });

            editor.menu.addSubMenuEntry(actions, "Reload", function(event) {
                ajax_script_do('reload', {});
            });

            function ajax_script_do(operation, attrs) {
                var name = editor.state.curDoc.name;
                $.ajax({
                    type : 'GET',
                    data : attrs,
                    url : operation + '/?name=' + name,
                    success : function(data, textStatus) {
                        editor.displayMessage(data.msg, textStatus);
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        editor.displayMessage(XMLHttpRequest.status + " " + XMLHttpRequest.statusText);
                    },
                });
                return false;
            }

            editor.menu.addSubMenuEntry(actions, "History", function(event) {
                HistoryViewer($(this), {
                    scriptEditor: editor,
                    mapping: {
                        idField: 'name'
                    }
                }, "scriptHistory");
            });


            sleep(1500).then(() => {
                var autoLoad = "${params.scriptName}";
                if (autoLoad.length) {
                    editor.open(autoLoad, false);
                }

                <g:each in="${scriptNames}" var="sName">
                editor.open("${sName}");</g:each>
            });

        });

</script>

</body>

</html>