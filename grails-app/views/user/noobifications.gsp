<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <title>Noobifications</title>
</head>

<body>

<section id="show-location" class="first">

    <g:form action="doSetNoobifications">
        <div class="card mt-2">
            <div class="card-header">Erinnerung an offene Tipps</div>
            <div class="card-body">
            <div class="form-check">
                <g:checkBox name="bla" value="${false}" disabled="disabled" />
                <label for="bla">Ich wünsche keine Beschimpfungen in Benachrichtigungen</label>
            </div>

            <div class="form-check">
                <g:checkBox name="nomail" value="${user.nomail}" />
                <label for="nomail">Ich wünsche keine Benachrichtigungen, werde aber weinen, wenn ich einen Tipp vergessen habe</label>
            </div>

            <button type="submit" class="btn btn-success mt-1">Speichern</button>
            </div>
        </div>
    </g:form>


</section>

</body>

</html>
