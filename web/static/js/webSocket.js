/**
 * Created by googo on 10/04/2017.
 */

var wsocket;    // Websocket connection


function connect() {
    // textarea = document.getElementById("textarea");
    // wsconsole = document.getElementById("wsconsole");
    // userlist = document.getElementById("userlist");
    wsocket = new WebSocket("ws://localhost:8080/trader/future");

    wsocket.onmessage = function(evt){
        onMessage(evt)
    };
    wsocket.onclose = function(evt){
        alert("websocket connection closed");
    };

    //document.getElementById("name").focus();
   // document.getElementById("consolediv").style.visibility = 'hidden';
}
/* Callback function for incoming messages
 * evt.data contains the message
 * Update the chat area, user's area and Websocket console */
var chartData;
function onMessage(evt) {
    var msg = evt.data;
    msg = eval("(" + msg + ")");
    if (msg.type === 2) {
        if (chartData.length >= 10) {
            for (var i = 0; i < chartData.length; i++) {
                if (i === chartData.length - 1) {
                    chartData[i] = msg.data;
                    break;
                }
                chartData[i] = chartData[i + 1];
            }
        }
        else {
            chartData[chartData.length] = msg.data;
        }
        formatChart(chartData);
    }
    if (msg.type === 1) {
        chartData = msg.data;
        formatChart(chartData);
    }
    if(msg.type ===3){
        formatStatus(msg.data);
    }
    console.log(chartData);

}


function cmtInfo(ele, type) {
    msgType = type;
    var data = {"data":ele, "type":type};
    wsocket.send(JSON.stringify(data));
}


function formatCmtTable(){
    var info = {"time":"3","broker1":"3","quantity1":"3", "bid":"3","ask":"3","quantity2":"3","broker2":"3","last":"3",};
    //var info = eval("(" + data + ")");

    var table = document.getElementById("cmt-info");
    table.innerHTML = "";
    for(var item in info){
        table.innerHTML += '<td>' + info[item] +'</td>';
    }
    table.innerHTML += '<td><a href="#" onclick="deleteOrder(this)" id="' +info["time"]+ '"> Delete</a></td>';
    //table.innerHTML+='</tr>';
}


function formatChart(data) {
    document.getElementById("morris-area-chart").innerHTML  = "";
    if(data != null){
        Morris.Area({
            element: 'morris-area-chart',
            data:data,
            xkey: 'CreatedAt',
            ykeys: ['Price'],
            labels: ['Price','FutureId'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true
        })
    }

}

function formatStatus(data) {
    document.getElementById("status").innerHTML = JSON.stringify(data);
}


/* Call connect() when the page first loads */
//window.addEventListener("load", connect, false);
connect();