<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Historical Weather Reporting System</title>
<link rel="stylesheet" type="text/css" href="../css/simple.css">
<link rel="stylesheet" href="../css/jquery-ui-1.11.4.custom/jquery-ui.min.css">
<script src="../javascript/jquery-2.1.3.min.js"></script>
<script src="../css/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script>
$(function() {
    $( "#st" ).buttonset();
    $( "#dt" ).buttonset();
  });
</script>
<% 
	session.setAttribute("currentModel", "report");
%>
</head>
<body>
<div id="header">
<h3>Historical Weather Reporting System</h3>
</div>
<div id="nav">
<jsp:include page="/template/header.jsp"></jsp:include>
</div>
<div id="section">
<table width="100%">
		<tr>
			<td width="35%">
				<div id="st">
					<input type="radio" id="st_1" name="st" value="Precipitation">
					<label for="st_1">Precipitation</label>
    				<input type="radio" id="st_2" name="st" checked="checked" value="CHDD">
    				<label for="st_2">CDD/HDD</label>
    				<input type="radio" id="st_3" name="st" value="Temperature">
    				<label for="st_3">Temperature</label>
				</div>
			</td>
			<td width="35%">
				<div id="dt">
					<input type="radio" id="dt_1" name="dt" value="Year">
					<label for="dt_1">Year</label>
    				<input type="radio" id="dt_2" name="dt" checked="checked" value="Monthly">
    				<label for="dt_2">Monthly</label>
    				<input type="radio" id="dt_3" name="dt" value="Quarterly">
    				<label for="dt_3">Quarterly</label>
				</div>
			</td>
			<td width="30%">
				<label for="region">Region: </label>
				<!--
  				<input id="region"> -->
  				<select name="region" id="region">
				     
				</select>
			</td>
			<td width="5%" align="left">
				<input type="button" value="query" id="query"/>
			</td>
		</tr>
</table>
<br>
<br>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto">
</div>
</div>
<div id="footer">
<jsp:include page="/template/footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
$(function () {
	$("#region").append($('<option/>', { 
        value: -1,
        text : 'Select a state' 
    }));
	$("input[type=button]").button();
	$.getJSON("<%=request.getContextPath()%>/json/region",  
			function( result ) {
				var i;
				for (i = 0; i < result.states.length; ++i) {
					$("#region").append($('<option/>', { 
				        value: result.states[i].code,
				        text : result.states[i].stateName 
				    }));
				} 
			});
	 
	
	//prepare query condition
	$("#query").bind("click",function(){
		var st=$('input[name="st"]:checked').val();
		var dt=$('input[name="dt"]:checked').val();
		var region=$("#region").val();
		$.getJSON("<%=request.getContextPath()%>/json/report", 
			{"st":st,"dt":dt,"region":region},
			function( result ) {
				 var data=result.data;
				 var title=data.title;
				 var yAxisTitle=data.yAxisTitle;
				 var series=data.series;
				 var categories=data.xAxis;
				 $('#container').highcharts({
				        title: {
				            text: title,
				            x: -20 //center
				        },
				        subtitle: {
				            text: 'Source: Historical Weather Database',
				            x: -20
				        },
				        xAxis: {
				            categories: categories
				        },
				        yAxis: {
				            title: {
				                text: yAxisTitle
				            },
				            plotLines: [{
				                value: 0,
				                width: 1,
				                color: '#808080'
				            }]
				        },
				        tooltip: {
				            valueSuffix: '°C'
				        },
				        legend: {
				            layout: 'vertical',
				            align: 'right',
				            verticalAlign: 'middle',
				            borderWidth: 0
				        },
				        series:series
				    });
			});
	});
});
</script>
</body>
</html>