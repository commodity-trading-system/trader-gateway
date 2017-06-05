<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CTS Trader</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/vendor/bootstrap-table-develop/dist/bootstrap-table.min.css" rel="stylesheet">



    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/static/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/dist/css/sb-admin-2.css" rel="stylesheet">

    <!--&lt;!&ndash; Morris Charts CSS &ndash;&gt;-->
    <link href="${pageContext.request.contextPath}/static/vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <!--<![endif]&ndash;&gt;-->
    <script src="${pageContext.request.contextPath}/static/js/stomp.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/webSocket.js"></script>
</head>

<body>
<!--&lt;!&ndash;-->

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" onclick="batch()">CTS Trader</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->
        </nav>

            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-header">Commodities Info</h2>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Commodity Order Table
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped" id="order-table">
                                            <thead>
                                            <tr>
                                                <th>Time</th>
                                                <th>Broker</th>
                                                <th>Quantity</th>
                                                <th>Bid</th>
                                                <th>Ask</th>
                                                <th>Quantity</th>
                                                <th>Broker</th>
                                                <th>Last</th>
                                                <th>Operation</th>
                                            </tr>
                                            </thead>
                                            <tbody id="cmt-info111">
                                                <tr id="cmt-info"></tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.col-lg-4 (nested) -->

                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Commodities
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        Commodities
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a href="#" id="1" onclick="cmtInfo(this.id, 1)">Apple</a>
                                        </li>
                                        <li><a href="#" id="2" onclick="cmtInfo(this.id, 1)">Banana</a>
                                        </li>
                                        <li><a href="#" id="3" onclick="cmtInfo(this.id, 1)">Orange</a>
                                        </li>
                                        <li><a href="#" id="4" onclick="cmtInfo(this.id, 1)">Pear</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="#" onclick="showOrderModal()">New Order</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div id="morris-area-chart"></div>
                            </div>

                        </div>
                        <!-- /.panel-body -->
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Deleted Orders Table
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" onclick="getDeletedOrder()">
                                        Refresh
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                            <tr>
                                                <th>Time</th>
                                                <th>Broker</th>
                                                <th>Quantity</th>
                                                <th>Bid</th>
                                                <th>Ask</th>
                                                <th>Quantity</th>
                                                <th>Broker</th>
                                                <th>Last</th>
                                                <th>Status</th>
                                            </tr>
                                            </thead>
                                            <tbody id="ord-info111">
                                            <tr id="ord-info"></tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.col-lg-4 (nested) -->

                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-8 -->
                <!-- /.col-lg-4 -->
            </div>
            <!-- /.row -->

<!-- /#wrapper -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    New Order
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Operation</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="operation">
                            <option value="1">Buy</option>
                            <option value="2">Sell</option>
                        </select>
                    </div>
                </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">Order Type</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="type">
                                <option value="1">Market</option>
                                <option value="2">Limit</option>
                                <option value="3">Stop</option>
                                <option value="4">Cancel</option>
                            </select>
                        </div>
                    </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Commodity</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="commodity" onchange="selectPeriod(this)">
                            <option value="Oil">Oil</option>
                            <option value="Oil">Banana</option>
                            <option value="Oil">Pear</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="period" class="col-sm-2 control-label">Period</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="period">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="quantity" class="col-sm-2 control-label">Quantity</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="quantity" placeholder="quantity">
                    </div>
                </div>
                <div class="form-group">
                    <label for="broker" class="col-sm-2 control-label">Broker</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="broker">
                            <option value="BrokerGateway1">Masami</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-sm-2 control-label">Price</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="price" placeholder="price">
                    </div>
                </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="submitOrder()">
                    提交更改
                </button>
                <p id="msg"></p>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="orderStatus" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    Order Status
                </h4>
            </div>
            <div class="modal-body">
                <center><p id="status"></p></center>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<script src="${pageContext.request.contextPath}/static/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/bootstrap-table-develop/dist/bootstrap-table.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/static/vendor/metisMenu/metisMenu.min.js"></script>

<!--&lt;!&ndash; Morris Charts JavaScript &ndash;&gt;-->
<script src="${pageContext.request.contextPath}/static/vendor/raphael/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/static/vendor/morrisjs/morris.min.js"></script>
<!--&lt;!&ndash; Custom Theme JavaScript &ndash;&gt;-->
<script src="${pageContext.request.contextPath}/static/dist/js/sb-admin-2.js"></script>



</body>
<script>
// Batch for showing
function generateConsignation(type, direction, quantity, price) {
    return {
        future: 1,
        side: direction,
        type: type,
        price: price,
        qty: quantity,
        brokerName: "BrokerGateway1"
    }
}

function sendConsignations( consignation, callback ) {
    console.log(consignation);
    $.ajax({
        url:"/trader/order/",
        type:'POST',
        data:JSON.stringify(consignation),
        dataType:'json',
        contentType: "application/json",
        success:function (info) {
            callback(info)
        }
    });
}

function batch() {
    cons = [];
    var TYPE_LIMIT = 2;
    var TYPE_MARKET = 1;
    var TYPE_STOP = 3;
    var SIDE_BUY = 1;
    var SIDE_SELL = 2;

    /**
     *  sell2   55  50
     *  sell1   53  200
     *  buy1    52  100
     *  buy1    50  100
     */
    cons.push(generateConsignation(TYPE_LIMIT, SIDE_BUY, 50, 100))
    cons.push(generateConsignation(TYPE_LIMIT, SIDE_BUY, 52, 100))
    cons.push(generateConsignation(TYPE_LIMIT, SIDE_SELL, 55, 50))
    cons.push(generateConsignation(TYPE_LIMIT, SIDE_SELL, 53, 200))

    // sell1 -100
    cons.push(generateConsignation(TYPE_LIMIT, SIDE_BUY, 53, 100))

    // buy1 -100
    // buy2 -50
    cons.push(generateConsignation(TYPE_MARKET, SIDE_SELL, 0, 150))

    cons.push(generateConsignation(TYPE_LIMIT, SIDE_BUY, 53, 150))

    cons.push(generateConsignation(TYPE_LIMIT, SIDE_SELL, 52, 200))
    cons.push(generateConsignation(TYPE_LIMIT, SIDE_SELL, 51, 200))

    cons.push(generateConsignation(TYPE_LIMIT, SIDE_BUY, 51, 100))

    for (let i = 0; i <= cons.length - 1; i++) {
        setTimeout(function() {
            sendConsignations(cons[i],function(res){
                console.log("Success:",res)
            })
        },2000)
    }
}

    function showOrderModal() {
        $('#myModal').modal('show');
        //alert($('#commodity').val());
    }
    formatChart();

    function submitOrder() {
        var operation = $('#operation').val();
        var commodity = $('#commodity').val();
        var type = $('#type').val();
        var period = $('#period').val();
        var quantity = document.getElementById("quantity").value;
        var broker = $('#broker').val();
        var price = document.getElementById("price").value;

        var data = {"future": period, "side":operation, "type":type, "price":price, "qty":quantity, "brokerName":broker};
        console.log(data);
        var url = "/trader/order/";
        $.ajax({
            url:url,
            type:'POST',
            data:JSON.stringify(data),
            dataType:'json',
            contentType: "application/json",
            success:function (info) {
                console.log(info);
                document.getElementById("msg").innerHTML = "Created!";
            }
            }

        )
        // var period = document.getElementById("period").value;
    }


    function selectPeriod() {
        var future = $('#commodity').val();
        var data = {"future": future};
        var url = "/trader/future/"+future;

        $.ajax({
                url:url,
                type:'GET',
                data:JSON.stringify(data),
                dataType:'json',
                contentType: "application/json",
                success:function (info) {
                    console.log(info);
                    var data = info;
                    document.getElementById("period").innerHTML = "";
                    for(var item in data){
                        console.log(item);
                        document.getElementById("period").innerHTML += "<option value='"+data[item]["id"] +"'>"+ data[item]["period"] +"</option>";
                    }
                }
            }

        )
    }
    selectPeriod();
    //formatCmtTable();

    function initOrder() {
        var url = "/trader/order/";
//        $.ajax({
//            url:url,
//            :'GET',
//
//        })
        $('#order-table').bootstrapTable({
            method:'GET',
            dataType:'json',
            url:url,
            search:true,
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            pageNumber:1,
//            responseHandler:responshandler,
            columns:[
                {
                    title:'Future',
                    field:'FutureId',
                    align:'center'
                },{
                    title:'Quantity',
                    field:'Quantity',
                    align:'center'
                },{
                    title:'ID',
                    field:'ID',
                    align:'center'
                },{
                    title:'Side',
                    field:'Type',
                    align:'center'
                },{
                    title:'Status',
                    field:'Status',
                    align:'center'
                },{
                    title:'Last Update',
                    field:'UpdatedAt',
                    align:'center'
                },{
                    title:'Open Quantity',
                    field:'OpenQuantity',
                    align:'center'
                },{
                    title:'Price',
                    field:'Price',
                    align:'center'
                },{
                    title:'Created Time',
                    field:'CreatedAt',
                    align:'center'
                },{
                    title:'Operation',
                    field:'ID',
                    align:'center',
                    formatter:function (value, row, index) {
                        var id = row.ID;
                        var e = '<a href="#" mce_href="#" onclick="cancelOrder(\''+ id + '\')">Cancel</a> ';
                        var d = '<a href="#" mce_href="#" onclick="detailOrder(\''+ id + '\')">Status</a> ';
                        return e + d;
                    }
                }]
        })
    }

    function cancelOrder(id) {

    }

    function detailOrder(id) {

    }

    initOrder();

    function cancelOrder(id) {
        var url = "/trader/order/" + id;
        $.ajax({
            url:url,
            type:'DELETE',
            success:function (info) {
                alert("Success " + info);
            }
        })

    }

    function detailOrder(id) {
        $('#orderStatus').modal('show');
        cmtInfo(id, 2);
    }

    //        {"time":3,"broker1":3,"quantity":3, "bid":3,"ask":3,"quantity":3,"broker2":3,"last":3},
    //        {"time":4,"broker1":4,"quantity":4, "bid":4,"ask":4,"quantity":4,"broker2":4,"last":4}
    //    ];
    // formatCmtTable(data);
</script>
</html>
