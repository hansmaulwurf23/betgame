Intro
=====

Betgame manages soccer bets. Bets and games are stored in a relational db (PostgreSQL). 

Games data
==========

Games data is coming from the webservice of the open data site of [OpenLigaDB](http://www.openligadb.de/).

Getting started
===============

All you need to do is 

1. Set up a database in Postgres and configure a DataSource in grails
2. Change leaguteShortcut and leagueSeason in OpenLigaDBService to match the cup you want to bet on
3. You probably want to remove the cas plugin and use local user accounts accordingly. Change SecurityService in services and resources.groovy in conf. Then remove the CasAuthService and set up spring security core in Config.groovy to match your needs. Finally change the actions in LoginController accordingly.
4. Uncomment openLigaDBService.fetchTeamsAndGamesAndLocations() in HomeController::index to import game data.

Frameworks
==========

Betgame uses
* Grails 2.5.3
* jQuery 1.11.3
* Bootstrap 3.3.6
* PostgreSQL
* Apache CXF
* Spring Security (Core and CAS)
