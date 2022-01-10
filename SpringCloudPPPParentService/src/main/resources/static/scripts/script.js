var ajaxRequest;  // The variable that makes Ajax possible!

//Browser Support Code
function ajaxFunction() {
    try {
        // Opera 8.0+, Firefox, Safari
        ajaxRequest = new XMLHttpRequest();
    } catch (e) {

        // Internet Explorer Browsers
        try {
            ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {

            try {
                ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {

                // Something went wrong
                alert("Your browser broke!");
                return false;
            }
        }
    }
}

function getSourceCodeStatus() {
    ajaxFunction();

    var e = document.getElementById("bookId");
    var value = e.options[e.selectedIndex].value;
    var text = e.options[e.selectedIndex].text;

    console.log('selected value : ' + value);
    console.log('selected text : ' + text);

    // Here processRequest() is the callback function.
    ajaxRequest.onreadystatechange = processRequestSourceCodeStatus;

    //if (!target) target = document.getElementById("userid");
    var url = "/bookRest/sourceCodeStatus/" + escape(value);
    console.log('url : ' + url);

    ajaxRequest.open("GET", url, true);
    ajaxRequest.send(null);
}

function processRequestSourceCodeStatus() {
    console.log('processRequestSourceCodeStatus() invoked');
    if (ajaxRequest.readyState == 4) {
        console.log('processRequest() readystate=4');
        console.log("Response status : "+ ajaxRequest.status);
        if (ajaxRequest.status == 200) {
            console.log("Response Text : "+ ajaxRequest.responseText);
            //ajaxDisplay.innerHTML = ajaxRequest.responseText;
            if(ajaxRequest.responseText=="true") {
                document.getElementById('sourceCodeDownloadYes').checked=true;
                document.getElementById('sourceCodeDownloadNo').checked=false;
            } else {
                document.getElementById('sourceCodeDownloadNo').checked=true;
                document.getElementById('sourceCodeDownloadYes').checked=false;
            }

        } else {
            console.log("Error occurred");
        }
    }
}

function searchBooks() {
    ajaxFunction();

    var searchTxtElement = document.getElementById("searchTxt");
    var searchTxtValue = searchTxtElement.value;

    console.log('searchTxtValue : ' + searchTxtValue);

    var searchCriteriaElement = document.getElementById("searchCriteria");
    var searchCriteriaValue = searchCriteriaElement.value;

    searchCriteriaValue = document.bookSearchForm.searchCriteria.value;

    console.log('searchCriteriaValue : ' + searchCriteriaValue);

    // Here processRequest() is the callback function.
    ajaxRequest.onreadystatechange = processRequestSearchBook;

    var searchTxtLength = searchTxtValue.trim().length;
    if(searchTxtLength < 3) {
        console.log("searchTxt is not having enough characters limit to search for.");
        return false;
    }

    //if (!target) target = document.getElementById("userid");
    var url = "/bookRest/search/" + escape(searchTxtValue) + "/" + escape(searchCriteriaValue);
    console.log('url : ' + url);

    ajaxRequest.open("GET", url, true);
    ajaxRequest.send(null);
}

function processRequestSearchBook() {
    console.log('processRequestSearchBook() invoked');
    if (ajaxRequest.readyState == 4) {
        var results = document.getElementById("results");
        console.log('processRequest() readystate=4');
        console.log("Response status : "+ ajaxRequest.status);
        if (ajaxRequest.status == 200) {
            console.log("Response Text : "+ ajaxRequest.responseText);
            var jsonResponse = JSON.parse(ajaxRequest.responseText);
            console.log("jsonResponse : ");
            console.log(jsonResponse);
            //results.innerHTML = ajaxRequest.responseText ;
            //results.innerHTML = getSampleHTMLTableForSearchResult(jsonResponse);
            var htmlTable = getHTMLTableForSearchResult(jsonResponse);
            console.log("HTML Table Output for Results :");
            console.log("--------------------------------");
            console.log(htmlTable);
            results.innerHTML = htmlTable;
        } else {
            console.log("Error occurred");
        }
    }
}

function changeRadio(){
    var radVal = document.bookSearchForm.searchCriteria.value;
    results.innerHTML = 'You selected: '+radVal;
}

function getSampleHTMLTableForSearchResult(searchResultJSON) {
    var htmlTable = "<table>" +
        "<tr>" +
            "<th>Id</th>" +
            "<th>Title</th>" +
            "<th>ISBN</th>" +
            "<th>Authors</th>" +
        "</tr>" +
        "<tr>" +
            "<td>1</td>" +
            "<td>Complete Reference Java</td>" +
            "<td>ISBN123</td>" +
            "<td>Herbert Schildt</td>" +
        "</tr>" +
        "</table>";

    return htmlTable;
}

function getHTMLTableForSearchResult(searchResultJSON) {

    var lengthDetails = "<b>Total # of matching rows : </b> " + searchResultJSON.length + "<br/>";

    var htmlTable = "<table>" +
        "<tr>" +
        "<th>Id</th>" +
        "<th>Title</th>" +
        "<th>ISBN</th>" +
        "<th>Authors</th>" +
        "</tr>";

    var jsonTableData = "";
    var id = '';
    for(var i=0; i < searchResultJSON.length; i++) {
        id = searchResultJSON[i].id;
        jsonTableData += "<tr>" +
                "<td>" +
                    "<a href='/book/get?id=" + id + "'>" + id +  "</a>" +
                "</td>" +
                "<td>" + searchResultJSON[i].title + "</td>" +
                "<td>" + searchResultJSON[i].isbn + "</td>" +
                "<td>" + searchResultJSON[i].authors + "</td>" +
            "</tr>";
    }
    htmlTable = htmlTable + jsonTableData +
        "</table>";

    htmlTable = lengthDetails + htmlTable;

    return htmlTable;
}