<g:set var="lang" value="${session.'org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE' ?: org.springframework.web.servlet.support.RequestContextUtils.getLocale(request).toString().substring(0,2)}"/>
<g:set var="currentURL" value="${request.forwardURI}"/>

<%--<ul class="nav secondary-nav language-dropdown pull-right">--%>
	<li class="dropdown dropdown-btn js-language-dropdown">
		<a class="dropdown-toggle" data-toggle="collapse" href="#langCollapse" role="button" aria-expanded="false" aria-controls="langCollapse">
			<asset:image class="" src="lang/${lang.toString()}.png" />
		</a>
		<ul class="collapse" role="menu" id="langCollapse">
			<g:set var="allLocales" value="${['en', 'de']}"/>
			<g:each status="i" var="locale" in="${allLocales}">
				<li><a class="js-language-link" title="${message(code: 'language.'+locale, default: locale)}" data-lang-code="${locale}" href="${currentURL+'?lang='+locale}">
					<asset:image class="" src="lang/${locale}.png" />
					<g:message code="language.${locale}" default="${locale}"/>
				</a></li>
			</g:each>

		</ul>
	</li>
<%--</ul>--%>