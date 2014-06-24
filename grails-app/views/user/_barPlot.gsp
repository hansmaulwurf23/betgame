<%@page import="grails.converters.JSON"%>
<asset:stylesheet src="jquery.jqplot.min.css"/>
<asset:javascript src="jqplot.js"/>

<g:set var="divID" value="${UUID.randomUUID()}" />

<div class="panel panel-default">
	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-12">
				${titel}
			</div>
		</div>
	</div>
	<div class="row condensed panel-body" id='${divID}'></div>
</div>

<script>
$(document).ready(function(){
    var plot1 = $.jqplot('${divID}', ${barData.data as JSON}, {
        // The "seriesDefaults" option is an options object that will
        // be applied to all series in the chart.
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            rendererOptions: {
                shadow: false,
                barPadding: 1,
                barMargin: 2
            }
        },
        // Custom labels for the series are specified with the "label"
        // option on the series option.  Here a series option object
        // is specified for each series.
        series:[
            <g:each in="${barData.labels}" var="l">
            {label:'${l}'},
            </g:each>
        ],
        seriesColors:['#428bca', '#5cb85c', '#d9534f', '#f0ad4e'],
        // Show the legend and put it outside the grid, but inside the
        // plot container, shrinking the grid to accomodate the legend.
        // A value of "outside" would not shrink the grid and allow
        // the legend to overflow the container.
        legend: {
            show: true,
            location: 'nw',
            placement: 'insideGrid'
        },
        grid: {
    	    drawGridLines: true,        // wether to draw lines across the grid or not.
    	        gridLineColor: '#AAA',   // CSS color spec of the grid lines.
    	        background: '#FFF',      // CSS color spec for background color of grid.
    	        borderColor: '#999999',     // CSS color spec for border around grid.
    	        borderWidth: 2.0,           // pixel width of border around grid.
    	        shadow: true,               // draw a shadow for grid.
    	        shadowAngle: 45,            // angle of the shadow.  Clockwise from x axis.
    	        shadowOffset: 1.5,          // offset from the line of the shadow.
    	        shadowWidth: 3,             // width of the stroke for the shadow.
    	        shadowDepth: 3
    	}, 
        axes: {
            // Use a category axis on the x axis and use our custom ticks.
            xaxis: {
                renderer: $.jqplot.CategoryAxisRenderer,
                ticks: ${(1..(barData.data[0].size())) as JSON}
            },
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            /*yaxis: {
                pad: 1.05,
                tickOptions: {formatString: '$%d'}
            }*/
        }
    });
});
</script>

