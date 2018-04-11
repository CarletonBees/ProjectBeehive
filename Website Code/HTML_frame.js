<html>
<head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
//header javascript code + initialization of Google Charts Libraries	
		google.charts.load('current', {'packages':['corechart']}); //load charts and packages
		google.charts.setOnLoadCallback(CreateHumidChart); //Draws Humidity chart when chart is loaded
		google.charts.setOnLoadCallback(CreateTempChart); //Draws Humidity chart when chart is loaded
	window.onmessage = (event) => {
		//if received a message from main website frame of a hive, that is the hive wish to be displayed
		if(event.data.trim() == "hive1" || event.data.trim() == "hive2" || event.data.trim() == "hive3" || event.data.trim() == "hive4" || event.data.trim() == "hive5" || event.data.trim() == "hive6" || event.data.trim() == "hive7" || event.data.trim() == "hive8" || event.data.trim() == "hive9" || event.data.trim() == "hive10"){
			window.parent.postMessage(event.data, "*");
			CreateHumidChart(event.data.trim(),1);
			CreateTempChart(event.data.trim(),1);
			in48hourmode = true;
		}
		if(event.data.trim() == "duration_hive1"){ //duration toggle was pressed
			if(in48hourmode == true){ // if 48 hour graph is being displayed
			in48hourmode = false;
			CreateHumidChart("hive1",2); // show the graph using long term data query
			CreateTempChart("hive1",2);
			return;
			}
			if(in48hourmode == false){ // if 48 hour graph is not being displayed
			CreateHumidChart("hive1",1); // show graph using short term data query
			CreateTempChart("hive1",1);
			in48hourmode = true;
			return;
			}
		}
		if(event.data.trim() == "duration_hive2"){ //duration toggle was pressed
			if(in48hourmode == true){ // if 48 hour graph is being displayed
			in48hourmode = false;
			CreateHumidChart("hive2",2); // show the graph using long term data query
			CreateTempChart("hive2",2);
			return;
			}
			if(in48hourmode == false){ // if 48 hour graph is not being displayed
			CreateHumidChart("hive2",1); // show graph using short term data query
			CreateTempChart("hive2",1);
			in48hourmode = true;
			return;
			}
		}
		if(event.data.trim() == "duration_hive3"){ //duration toggle was pressed
			if(in48hourmode == true){ // if 48 hour graph is being displayed
			in48hourmode = false;
			CreateHumidChart("hive3",2); // show the graph using long term data query
			CreateTempChart("hive3",2);
			return;
			}
			if(in48hourmode == false){ // if 48 hour graph is not being displayed
			CreateHumidChart("hive3",1); // show graph using short term data query
			CreateTempChart("hive3",1);
			in48hourmode = true;
			return;
			}
		}
		if(event.data.trim() == "duration_hive4"){ //duration toggle was pressed
			if(in48hourmode == true){ // if 48 hour graph is being displayed
			in48hourmode = false;
			CreateHumidChart("hive4",2); // show the graph using long term data query
			CreateTempChart("hive4",2);
			return;
			}
			if(in48hourmode == false){ // if 48 hour graph is not being displayed
			CreateHumidChart("hive4",1); // show graph using short term data query
			CreateTempChart("hive4",1);
			in48hourmode = true;
			return;
			}
		}
    };
	//Query of url for humidity values
    function CreateHumidChart(hiveId, duration) {
		//48 Hour Short Query, using 96 readings in a column
		if(duration == 1){
			var query = new google.visualization.Query("https://docs.google.com/spreadsheets/d/192PI2xfYmpF3hQNbOFuJOV0LnDJKKGkCJyiFe698RU0/gviz/tq?sheet="+hiveId+"&headers=1&range="+hiveId+"!B1:B96,"+hiveId+"!C1:C96,"+hiveId+"!D1:D96,"+hiveId+"!E1:E96");
			query.send(handleHUMIDSampleDataQueryResponse);
		}
		//All long Query, using all the readings present in a column
		if(duration == 2){
			var query = new google.visualization.Query("https://docs.google.com/spreadsheets/d/192PI2xfYmpF3hQNbOFuJOV0LnDJKKGkCJyiFe698RU0/gviz/tq?sheet="+hiveId+"&headers=1&range="+hiveId+"!B1:B700,"+hiveId+"!C1:C700,"+hiveId+"!D1:D700,"+hiveId+"!E1:E700");
			query.send(handleHUMIDSampleDataQueryResponse);
		}
    }
	//Query of url for temperature values
	function CreateTempChart(hiveId, duration) {
		//48 Hour Short Query, using 96 readings in a column
		if(duration == 1){
			var query = new google.visualization.Query("https://docs.google.com/spreadsheets/d/192PI2xfYmpF3hQNbOFuJOV0LnDJKKGkCJyiFe698RU0/gviz/tq?sheet="+hiveId+"&headers=1&range="+hiveId+"!H1:H96,"+hiveId+"!I1:I96,"+hiveId+"!J1:J96,"+hiveId+"!K1:96");
			query.send(handleTEMPSampleDataQueryResponse);
		}
		//All long Query, using all the readings present in a column
		if(duration == 2){
			var query = new google.visualization.Query("https://docs.google.com/spreadsheets/d/192PI2xfYmpF3hQNbOFuJOV0LnDJKKGkCJyiFe698RU0/gviz/tq?sheet="+hiveId+"&headers=1&range="+hiveId+"!H1:H700,"+hiveId+"!I1:I700,"+hiveId+"!J1:J700,"+hiveId+"!K1:700");
			query.send(handleTEMPSampleDataQueryResponse);
		}
    }	

	//Response to humidity query with chart creation
    function handleHUMIDSampleDataQueryResponse(response) {
		if (response.isError()) {
			alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
			return;
		}
		//chart specifications and parameters (legend, titles, formatting)
		var options = {
			title: ' Humidity Values ', 'width': 750, 'height': 450,
			chartArea: {left:75, top:30, bottom:120, right: 5,  width:"100%", height: "65%"},
			hAxis: {slantedText: true, slantedTextAngle: 45, title: 'Time of Day', format: 'M:d:yyyy HH:MM', bold:'false', gridlines: { count: 15 } },
			vAxis: {title: 'Humidity (RH %)', format: '0.00', bold:'false'},
			legend: {position: 'bottom'}
		};
		//actual drawing of humidity chart
		var data = response.getDataTable();
		var chart = new google.visualization.LineChart(document.getElementById('humid_chart_div'));
		chart.draw(data, options);
    }

	//Response to humidity query with chart creation
    function handleTEMPSampleDataQueryResponse(response) {
		if (response.isError()) {
			alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
			return;
		}
		//chart specifications and parameters (legend, titles, formatting)
		var options = {
			title: ' Temeprature Values ', 'width': 750, 'height': 450,
			chartArea: {left:70, top:20, right: 5,  width:"100%", height: "65%"},
			hAxis: {slantedText: true, slantedTextAngle: 45, title: 'Time of Day', format: 'M:d:yyyy HH:MM', bold:'false', gridlines: { count: 15 }},
			vAxis: {title: 'Temperature', format: '0.00', bold:'false'},
			legend: {position: 'bottom'}
		};
		//actual temperature of humidity chart
		var data = response.getDataTable();
		var chart = new google.visualization.LineChart(document.getElementById('temp_chart_div'));
		chart.draw(data, options);
    }
	//bold:'false', gridlines: { count: 15 },type: 'category', viewWindow: { min:1, max: 30 }},
//footer javascript code	
  </script>
</head>
<body>
     <div id= "humid_chart_div" style="display:inline-block; width: 600px; height: 500px"></div>
     <div id= "temp_chart_div" style="display:inline-block; width: 600px; height: 500px"></div>

</body>
</html>
