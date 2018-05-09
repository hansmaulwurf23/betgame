<%@page import="grails.converters.JSON"%>
<asset:stylesheet src="jquery.jqplot.min.css"/>
<asset:javascript src="jqplot.js"/>

<g:set var="divID" value="${UUID.randomUUID()}" />

<div class="card ">
	<div class="card-header">
		<div class="row">
			<div class="col col-xs-12">
				${titel}
			</div>
		</div>
	</div>
	<div class="row condensed card-body" id='${divID}'></div>
</div>

<script>
$(document).ready(function(){
  var data = [
    <g:each in="${distrData}" var="q">
    	['${q.key}', ${q.value}],
    </g:each>
  ];
  var plot1 = jQuery.jqplot ('${divID}', [data],
    {
      seriesDefaults: {
        // Make this a pie chart.
        renderer: jQuery.jqplot.PieRenderer,
        rendererOptions: {
          startAngle: -90,
          // Put data labels on the pie slices.
          // By default, labels show the percentage of the slice.
          showDataLabels: true,
          shadow: false
        }
      },
      seriesColors:['#d9534f', '#f0ad4e', '#5cb85c', '#aaa'],
      grid: {
    	    drawGridLines: true,        // wether to draw lines across the grid or not.
    	        gridLineColor: '#000',   // CSS color spec of the grid lines.
    	        background: '#FFF',      // CSS color spec for background color of grid.
    	        borderColor: '#999999',     // CSS color spec for border around grid.
    	        borderWidth: 2.0,           // pixel width of border around grid.
    	        shadow: true,               // draw a shadow for grid.
    	        shadowAngle: 45,            // angle of the shadow.  Clockwise from x axis.
    	        shadowOffset: 1.5,          // offset from the line of the shadow.
    	        shadowWidth: 3,             // width of the stroke for the shadow.
    	        shadowDepth: 3
    	}, 
      legend: { 
    	  renderer: $.jqplot.EnhancedLegendRenderer,
          show:true, location: 's',
          rendererOptions: {
              numberRows: 1
          }
      }
    }
  );
});
</script>
