<#macro page>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>currere</title>
    </head>
    <body>
    <#nested>
    </body>
    <style type="text/css">
        .table {
            width: 50%;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-collapse: collapse;
        }
        .table th {
            font-weight: bold;
            padding: 5px;
            background: #efefef;
            border: 1px solid #dddddd;
        }
        .table td {
            border: 1px solid #dddddd;
            padding: 5px;
        }
    </style>
    </html>
</#macro>