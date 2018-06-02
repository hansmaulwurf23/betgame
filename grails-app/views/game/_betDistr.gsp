<div class="card border-secondary">
	<div class="card-header">
		<div class="row">
			<div class="col col-xs-6 text-right">
				${message(code: 'bet', default: 'Tipp')}
			</div>
			<div class="col col-xs-6 text-left">
				${message(code: 'count', default: 'Anzahl')}
			</div>
		</div>
	</div>
    <div class="card-body">
	<g:each in="${betDistr}" var="b">
	<div class="row condensed">
		<div class="col col-xs-6 text-right">
			${b.key}
		</div>
		<div class="col col-xs-6 text-left">
			${b.value} <small>(${(b.value.toDouble()  / total * 100).round(1)}%)</small>
		</div>
	</div>
	</g:each>
    </div>
</div>
