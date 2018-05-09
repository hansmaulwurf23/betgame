<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <title>Country Names</title>

    <g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>

    <script>
        $(document).ready(function() {
           $('input#search').keyup(function() {
                console.log($(this).val());
                $('table#cnTable tbody tr').hide();
                $('table#cnTable tbody tr:contains("'+$(this).val()+'")').show();
           });

           $('table#cnTable tbody tr').dblclick(function() {
              $('select#locale').val($(this).attr('data-locale'));
              $('input#name').val($(this).attr('data-name'));
              $('input#code').val($(this).attr('data-code'));
           });
        });
    </script>
</head>

<body>

<section class="first">
    <div class="container">
        <g:form action="updateCountryName" enctype='multipart/form-data' class="form-inline" >
            <div class="form-group my-2">
                <label for="code" class="sr-only">Code</label>
                <g:textField name="code" class="form-control" placeholder="Code" />
            </div>
            <div class="form-group mx-sm-3 my-2 input-group">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="locale">Locale</label>
                </div>
                <select class="custom-select" id="locale" name="locale">
                    <option value="de_DE" selected>de_DE</option>
                    <option value="en_GB">en_GB</option>
                </select>
            </div>
            <div class="form-group my-2 flex-grow-1">
                <label for="name" class="sr-only">Name</label>
                <g:textField name="name" class="form-control flex-grow-1" placeholder="Name" />
            </div>
            <button type="submit" class="btn btn-primary m-2">Save</button>
            <div class="form-group my-2 flex-grow-1">
                <label for="search" class="sr-only">Search</label>
                <g:textField name="search" class="form-control flex-grow-1" placeholder="Search" />
            </div>
        </g:form>

        <table class="table table-sm" id="cnTable">
            <thead class="thead-dark">
                <tr>
                    <th>Code</th>
                    <th>Locale</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody>
            <g:each in="${countryNames}" var="c">
                <tr data-locale="${c.locale}" data-code="${c.code}" data-name="${c.name}">
                    <td>${c.code}</td>
                    <td>${c.locale}</td>
                    <td>${c.name}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</section>

</body>

</html>
