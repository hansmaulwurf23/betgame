<div class="panel panel-default">
	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-6 text-right">
				${message(code: 'bet', default: 'Tipp')}
			</div>
			<div class="col-xs-6 text-left">
				${message(code: 'count', default: 'Anzahl')}
			</div>
		</div>
	</div>
	<g:each in="${betDistr}" var="b">
	<div class="row condensed panel-body">
		<div class="col-xs-6 text-right">
			${b.key}
		</div>
		<div class="col-xs-6 text-left">
			${b.value} <small>(${(b.value.toDouble()  / total * 100).round(1)}%)</small>
		</div>
	</div>
	</g:each>
</div>
