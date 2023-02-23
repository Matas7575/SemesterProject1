document.addEventListener("DOMContentLoaded", function() { // when page loads
    var document, DOMParser;
//readXML();
readClassXML();
  });

  // This function gets all the information on the xml file
  function readClassXML() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            readClasses(this);
        }
    };
    xhttp.open("GET", "../src/classxyz.xml", true);
    xhttp.send();
}

//this function filters the information of the xml
function readClasses(xml) {
    var xmlDoc = xml.responseXML; // gets the response
    var Classs = $(xmlDoc).find("Class"); // finds the "Class" Object
    var classxyz = $("#classxyz"); // Finds where to add that information, in this case it's a select box
    Classs.each(function () { // For every found "Class" will add an option for the select
        var option = $("<option />");
        option.html($(this).find("ClassName").text());
        classxyz.append(option);
    });
}

// When changing the table's data, this functions clears all information, replacing with a default table so there is no conflicts
function redoTable() {
    document.getElementById("tableContainer").innerHTML = '<table class="table table-bordered text-center"> <thead class="align-middle"> <tr class=""> <th class="width100" id="weekcell">WEEK</th> <th class="width250">MON</th> <th class="width250">TUE</th> <th class="width250">WED</th> <th class="width250">THU</th> <th class="width250">FRI</th> </tr> </thead> <tbody> <tr> <td>8:20<br>9:05</td> <td id="MON820"></td> <td id="TUE820"></td> <td id="WED820"></td> <td id="THU820"></td> <td id="FRI820"></td> </tr> <tr> <td>9:10<br>9:55</td> <td id="MON910"></td> <td id="TUE910"></td> <td id="WED910"></td> <td id="THU910"></td> <td id="FRI910"></td> </tr> <tr> <td>10:15<br>11:00</td> <td id="MON1015"></td> <td id="TUE1015"></td> <td id="WED1015"></td> <td id="THU1015"></td> <td id="FRI1015"></td> </tr> <tr> <td>11:05<br>11:50</td> <td id="MON1105"></td> <td id="TUE1105"></td> <td id="WED1105"></td> <td id="THU1105"></td> <td id="FRI1105"></td> </tr> <tr> <td>11:55<br>12:40</td> <td id="MON1155"></td> <td id="TUE1155"></td> <td id="WED1155"></td> <td id="THU1155"></td> <td id="FRI1155"></td> </tr> <tr> <td>12:45<br>13:30</td> <td id="MON1245"></td> <td id="TUE1245"></td> <td id="WED1245"></td> <td id="THU1245"></td> <td id="FRI1245"></td> </tr> <tr> <td>13:35<br>14:20</td> <td id="MON1335"></td> <td id="TUE1335"></td> <td id="WED1335"></td> <td id="THU1335"></td> <td id="FRI1335"></td> </tr> <tr> <td>14:30<br>15:15</td> <td id="MON1430"></td> <td id="TUE1430"></td> <td id="WED1430"></td> <td id="THU1430"></td> <td id="FRI1430"></td> </tr> <tr> <td>15:20<br>16:05</td> <td id="MON1520"></td> <td id="TUE1520"></td> <td id="WED1520"></td> <td id="THU1520"></td> <td id="FRI1520"></td> </tr> <tr> <td>16:10<br>16:55</td> <td id="MON1610"></td> <td id="TUE1610"></td> <td id="WED1610"></td> <td id="THU1610"></td> <td id="FRI1610"></td> </tr> <tr> <td>17:00<br>17:45</td> <td id="MON1700"></td> <td id="TUE1700"></td> <td id="WED1700"></td> <td id="THU1700"></td> <td id="FRI1700"></td> </tr> <tr> <td>18:00<br>18:45</td> <td id="MON1800"></td> <td id="TUE1800"></td> <td id="WED1800"></td> <td id="THU1800"></td> <td id="FRI1800"></td> </tr> </tbody></table>';
}

function readXML() {
    redoTable(); // Before everything rset the table to default 
    var xhttp = new XMLHttpRequest(); // Get the XML
    xhttp.onreadystatechange = function () { // When loaded...
        if (this.readyState == 4 && this.status == 200) {
            showData(this);
        }
    };
    var selectedValue = document.getElementById("classxyz").value; // get the class selected by the dropdown ( select menu )
    var filepath = "../src/"    //    --
    filepath += selectedValue   //    ----- Concat the directory with the class name and file type
    filepath += ".xml"          //    --
    xhttp.open("GET", filepath, true); 
    xhttp.send(); // get the right file 
}


// ARROWS

// When trigger (clicked the arrow icon)
function nextWeek()
{
    $("#weeks > option:selected") // gets option selected 
    .prop("selected", false) // removed the selected property 
    .next() // goes to the next opton
    .prop("selected", true); // add the selected property 
    readXML(); 
}
function prevWeek()
{
    $("#weeks > option:selected")
    .prop("selected", false)
    .prev() // gets the prev option
    .prop("selected", true);
    readXML();
}

function showData(xml) {
    var xmlDoc = xml.responseXML; // gets the file from above
    var x = xmlDoc.getElementsByTagName("sessionList"); // gets all the Session Lists object from the xml file
    var numSessions = x.length; // gets hoe many sessions he file has

    for (var i = 0; i < numSessions; i++) {
        var selectedWeek = document.getElementById("weeks").value; // gets the week selected by the user on the selection box
        var weekNumber = x[i].getElementsByTagName("weekNumber")[0].childNodes[0].nodeValue; // finds the week number on the xml 
        if (weekNumber == selectedWeek) { // if they match

            var courseTitle = x[i].getElementsByTagName("courseTitle")[0].childNodes[0].nodeValue; // gets course title

            var dayOfTheWeek = x[i].getElementsByTagName("dayOfTheWeek")[0].childNodes[0].nodeValue; // gets the day of the week
            var numberOfLessons = x[i].getElementsByTagName("numberOfLessons")[0].childNodes[0].nodeValue; // how many lessons
            var hour = x[i].getElementsByTagName("startTime")[0].getElementsByTagName("hour")[0].childNodes[0].nodeValue; // when the session start
            var minutes = x[i].getElementsByTagName("startTime")[0].getElementsByTagName("minute")[0].childNodes[0].nodeValue; // when the session start 
            if (parseInt(minutes) < 10) { // if the minute ends with only one number add a "0" before (So it's not 5 it's 05)
                minutes = "0" + minutes;
            }
            var startTime = hour + ":" + minutes; // concats the hout with the minute

            var endTime = ""; // resets the end time 
            switch (startTime) {  //depending the start hour find how many lessons it has to set the endTIme
                case ("8:20"):
                    if (numberOfLessons == 1) {
                        endTime = "9:05";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "9:55";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "11:00";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "11:50";
                    }
                    if (numberOfLessons == 5) {
                        endTime = "12:40";
                    }
                    if (numberOfLessons == 6) {
                        endTime = "13:30";
                    }
                    break;

                case ("9:10"):

                    if (numberOfLessons == 1) {
                        endTime = "9:55";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "11:00";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "11:50";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "12:40";
                    }
                    if (numberOfLessons == 5) {
                        endTime = "13:30";
                    }
                    if (numberOfLessons == 6) {
                        endTime = "14:20";
                    }
                    break;

                case ("10:15"):

                    if (numberOfLessons == 1) {
                        endTime = "11:00";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "11:50";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "12:40";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "13:30";
                    }
                    if (numberOfLessons == 5) {
                        endTime = "14:20";
                    }
                    if (numberOfLessons == 6) {
                        endTime = "15:15";
                    }
                    break;

                case ("11:05"):


                    if (numberOfLessons == 1) {
                        endTime = "11:50";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "12:40";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "13:30";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "14:20";
                    }
                    if (numberOfLessons == 5) {
                        endTime = "15:15";
                    }
                    if (numberOfLessons == 6) {
                        endTime = "16:05";
                    }
                    break;

                case ("11:55"):
                    if (numberOfLessons == 1) {
                        endTime = "12:40";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "13:30";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "14:20";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "15:15";
                    }
                    if (numberOfLessons == 5) {
                        endTime = "16:05";
                    }
                    if (numberOfLessons == 6) {
                        endTime = "16:55";
                    }
                    break;

                case ("12:45"):
                    if (numberOfLessons == 1) {
                        endTime = "13:30";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "14:20";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "15:15";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "16:05";
                    }
                    if (numberOfLessons == 5) {
                        endTime = "16:55";
                    }
                    if (numberOfLessons == 6) {
                        endTime = "17:45";
                    }
                    break;

                case ("13:35"):
                    if (numberOfLessons == 1) {
                        endTime = "14:20";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "15:15";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "16:05";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "16:55";
                    }
                    if (numberOfLessons == 5) {
                        endTime = "17:45";
                    }
                    if (numberOfLessons == 6) {
                        endTime = "18:45";
                    }
                    break;

                case ("14:30"):
                    if (numberOfLessons == 1) {
                        endTime = "15:15";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "16:05";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "16:55";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "11:50";
                    }
                    if (numberOfLessons == 5) {
                        endTime = "17:45";
                    }
                    if (numberOfLessons == 6) {
                        endTime = "18:45";
                    }
                    break;

                case ("15:20"):
                    if (numberOfLessons == 1) {
                        endTime = "16:05";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "16:55";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "17:45";
                    }
                    if (numberOfLessons == 4) {
                        endTime = "18:45";
                    }
                    break;

                case ("16:10"):
                    if (numberOfLessons == 1) {
                        endTime = "16:55";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "17:45";
                    }
                    if (numberOfLessons == 3) {
                        endTime = "18:45";
                    }

                    break;

                case ("17:00"):
                    if (numberOfLessons == 1) {
                        endTime = "17:45";
                    }
                    if (numberOfLessons == 2) {
                        endTime = "18:45";
                    }
                    break;

                case ("18:00"): {
                    endTime = "18:45"
                }
                break;

            }

            var classroom = x[i].getElementsByTagName("classroom")[0].getElementsByTagName("room")[0].childNodes[0].nodeValue; // get's the classroom

            var tdID = dayOfTheWeek + "" + hour + "" + minutes; // concats the information from the xml so it knows where to put the information with the methods bellow

            document.getElementById(tdID).innerHTML = courseTitle + "<br>" + startTime + " - " + endTime + "<br>" + classroom; 
            document.getElementById(tdID).setAttribute("rowspan", numberOfLessons);
            document.getElementById(tdID).style.color = "white";
            if (courseTitle == "RWD") // depending the course, set a background color for the td
                document.getElementById(tdID).style.backgroundColor = "#989b83";
            if (courseTitle == "SEP")
                document.getElementById(tdID).style.backgroundColor = "#bd543a";
            if (courseTitle == "DMA")
                document.getElementById(tdID).style.backgroundColor = "#ebb857";
            if (courseTitle == "SDJ")
                document.getElementById(tdID).style.backgroundColor = "#386187";





            
            switch (tdID) {
                case "MON820":
                    if (numberOfLessons > 1) {
                        document.getElementById("MON910").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1015").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1105").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("MON1155").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("MON1245").remove();
                    }

                    break;

                case "TUE820":
                    if (numberOfLessons > 1) {
                        document.getElementById("TUE910").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1015").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("TUE1105").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("TUE1155").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("TUE1245").remove();
                    }

                    break;

                case "WED820":
                    if (numberOfLessons > 1) {
                        document.getElementById("WED910").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1015").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("WED1105").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("WED1155").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("WED1245").remove();
                    }
                    break;

                case "THU820":
                    if (numberOfLessons > 1) {
                        document.getElementById("THU910").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1015").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("THU1105").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("THU1155").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("THU1245").remove();
                    }

                    break;

                case "FRI820":
                    if (numberOfLessons > 1) {
                        document.getElementById("FRI910").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1015").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("FRI1105").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("FRI1155").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("FRI1245").remove();
                    }
                    break;

                case "MON910":
                    if (numberOfLessons > 1) {
                        document.getElementById("MON1015").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1105").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1155").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("MON1245").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("MON1335").remove();
                    }
                    break;

                case "TUE910":
                    if (numberOfLessons > 1) {
                        document.getElementById("TUE1015").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1105").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("TUE1155").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("TUE1245").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("TUE1335").remove();
                    }
                    break;

                case "WED910":
                    if (numberOfLessons > 1) {
                        document.getElementById("WED1015").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1105").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("WED1155").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("WED1245").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("WED1335").remove();
                    }
                    break;

                case "THU910":
                    if (numberOfLessons > 1) {
                        document.getElementById("THU1015").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1105").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("THU1155").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("THU1245").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("THU1335").remove();
                    }
                    break;

                case "FRI910":
                    if (numberOfLessons > 1) {
                        document.getElementById("FRI1015").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1105").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("FRI1155").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("FRI1245").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("FRI1335").remove();
                    }
                    break;

                case "MON1015":

                    if (numberOfLessons > 1) {
                        document.getElementById("MON1105").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1155").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1245").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("MON1335").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("MON1430").remove();
                    }
                    break;
                case "TUE1015":

                    if (numberOfLessons > 1) {
                        document.getElementById("TUE1105").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1155").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("TUE1245").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("TUE1335").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("TUE1430").remove();
                    }
                    break;


                case "WED1015":

                    if (numberOfLessons > 1) {
                        document.getElementById("WED1105").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1155").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("WED1245").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("WED1335").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("WED1430").remove();
                    }
                    break;

                case "THU1015":

                    if (numberOfLessons > 1) {
                        document.getElementById("THU1105").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1155").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("THU1245").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("THU1335").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("THU1430").remove();
                    }
                    break;

                case "FRI1015":

                    if (numberOfLessons > 1) {
                        document.getElementById("FRI1105").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1155").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("FRI1245").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("FRI1335").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("FRI1430").remove();
                    }
                    break;

                case "MON1105":

                    if (numberOfLessons > 1) {
                        document.getElementById("MON1155").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1245").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1335").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("MON1430").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("MON1520").remove();
                    }
                    break;



                case "MON1155":
                    if (numberOfLessons > 1) {
                        document.getElementById("MON1245").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1335").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1430").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("MON1520").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("MON1610").remove();
                    }
                    break;

                case "TUE1155":
                    if (numberOfLessons > 1) {
                        document.getElementById("TUE1245").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1335").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("TUE1430").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("TUE1520").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("TUE1610").remove();
                    }
                    break;

                case "WED1155":
                    if (numberOfLessons > 1) {
                        document.getElementById("WED1245").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1335").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("WED1430").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("WED1520").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("WED1610").remove();
                    }
                    break;

                case "THU1155":
                    if (numberOfLessons > 1) {
                        document.getElementById("THU1245").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1335").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("THU1430").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("THU1520").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("THU1610").remove();
                    }
                    break;

                case "FRI1155":
                    if (numberOfLessons > 1) {
                        document.getElementById("FRI1245").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1335").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("FRI1430").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("FRI1520").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("FRI1610").remove();
                    }
                    break;

                case "MON1245":

                    if (numberOfLessons > 1) {
                        document.getElementById("MON1335").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1430").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1520").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("MON1610").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("MON1700").remove();
                    }
                    break;


                case "TUE1245":

                    if (numberOfLessons > 1) {
                        document.getElementById("TUE1335").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1430").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("TUE1520").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("TUE1610").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("TUE1700").remove();
                    }
                    break;


                case "WED1245":

                    if (numberOfLessons > 1) {
                        document.getElementById("WED1335").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1430").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("WED1520").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("WED1610").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("WED1700").remove();
                    }
                    break;


                case "THU1245":

                    if (numberOfLessons > 1) {
                        document.getElementById("THU1335").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1430").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("THU1520").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("THU1610").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("THU1700").remove();
                    }
                    break;


                case "FRI1245":

                    if (numberOfLessons > 1) {
                        document.getElementById("FRI1335").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1430").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("FRI1520").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("FRI1610").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("FRI1700").remove();
                    }
                    break;

                case "MON1335":

                    if (numberOfLessons > 1) {
                        document.getElementById("MON1430").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1520").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1610").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("MON1700").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("MON1800").remove();
                    }
                    break;
                case "TUE1335":

                    if (numberOfLessons > 1) {
                        document.getElementById("TUE1430").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1520").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("TUE1610").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("TUE1700").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("TUE1800").remove();
                    }
                    break;
                case "WED1335":

                    if (numberOfLessons > 1) {
                        document.getElementById("WED1430").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1520").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("WED1610").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("WED1700").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("WED1800").remove();
                    }
                    break;
                case "THU1335":

                    if (numberOfLessons > 1) {
                        document.getElementById("THU1430").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1520").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("THU1610").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("THU1700").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("THU1800").remove();
                    }
                    break;
                case "FRI1335":

                    if (numberOfLessons > 1) {
                        document.getElementById("FRI1430").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1520").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("FRI1610").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("FRI1700").remove();
                    }
                    if (numberOfLessons > 5) {
                        document.getElementById("FRI1800").remove();
                    }
                    break;

                case "MON1430":

                    if (numberOfLessons > 1) {
                        document.getElementById("MON1520").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1610").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1700").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("MON1800").remove();
                    }
                    break;
                case "TUE1430":

                    if (numberOfLessons > 1) {
                        document.getElementById("TUE1520").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1610").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("TUE1700").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("TUE1800").remove();
                    }
                    break;
                case "WED1430":

                    if (numberOfLessons > 1) {
                        document.getElementById("WED1520").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1610").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("WED1700").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("WED1800").remove();
                    }
                    break;
                case "THU1430":

                    if (numberOfLessons > 1) {
                        document.getElementById("THU1520").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1610").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("THU1700").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("THU1800").remove();
                    }
                    break;
                case "FRI1430":

                    if (numberOfLessons > 1) {
                        document.getElementById("FRI1520").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1610").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("FRI1700").remove();
                    }
                    if (numberOfLessons > 4) {
                        document.getElementById("FRI1800").remove();
                    }
                    break;

                case "MON1520":

                    if (numberOfLessons > 1) {
                        document.getElementById("MON1610").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1700").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("MON1800").remove();
                    }
                    break;

                case "TUE1520":

                    if (numberOfLessons > 1) {
                        document.getElementById("TUE1610").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1700").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("TUE1800").remove();
                    }
                    break;

                case "WED1520":

                    if (numberOfLessons > 1) {
                        document.getElementById("WED1610").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1700").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("WED1800").remove();
                    }
                    break;

                case "THU1520":

                    if (numberOfLessons > 1) {
                        document.getElementById("THU1610").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1700").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("THU1800").remove();
                    }
                    break;

                case "FRI1520":

                    if (numberOfLessons > 1) {
                        document.getElementById("FRI1610").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1700").remove();
                    }
                    if (numberOfLessons > 3) {
                        document.getElementById("FRI1800").remove();
                    }
                    break;

                case "MON1610":

                    if (numberOfLessons > 1) {
                        document.getElementById("MON1700").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("MON1800").remove();
                    }
                    break;
                case "TUE1610":

                    if (numberOfLessons > 1) {
                        document.getElementById("TUE1700").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("TUE1800").remove();
                    }
                    break;
                case "WED1610":

                    if (numberOfLessons > 1) {
                        document.getElementById("WED1700").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("WED1800").remove();
                    }
                    break;
                case "THU1610":

                    if (numberOfLessons > 1) {
                        document.getElementById("THU1700").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("THU1800").remove();
                    }
                    break;
                case "FRI1610":

                    if (numberOfLessons > 1) {
                        document.getElementById("FRI1700").remove();
                    }
                    if (numberOfLessons > 2) {
                        document.getElementById("FRI1800").remove();
                    }
                    break;

                case "MON1700":
                    if (numberOfLessons > 1) {
                        document.getElementById("MON1800").remove();
                    }

                    case "TUE1700":
                        if (numberOfLessons > 1) {
                            document.getElementById("TUE1800").remove();
                        }

                        case "WED1700":
                            if (numberOfLessons > 1) {
                                document.getElementById("WED1800").remove();
                            }

                            case "THU1700":
                                if (numberOfLessons > 1) {
                                    document.getElementById("THU1800").remove();
                                }

                                case "FRI1700":
                                    if (numberOfLessons > 1) {
                                        document.getElementById("FRI1800").remove();
                                    }

            }
        }
    }
}