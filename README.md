# betgame

Betgame is a Grails 3.x web application to manage bets for Soccer Cups (i.e. EM2016/WM2018) using the https://www.openligadb.de/ data.

# Authentication

Authentication is done via Spring Security CAS. If not configured it is not used. Fallback is done via `grails-app/domain/sec/*` domain classes. These should be provided from the database accordingly.

# Requirements

+ PostgreSQL DB

# Installation

1. Create a DB
2. Configure the DataSource (i.e. in the classpath or `/opt/rrzepp/apps/betgame/internal/betgame-config.groovy`) and make sure dbCreate is set to `update`
3. Check [`data/scripts/`](https://github.com/hansmaulwurf23/betgame/tree/master/data/scripts) and import all scripts into the scripting eninge. Run `OpenLigaDB` once and `fetchGameScores` on a regular basis.

# Running

Move one foot in front of the other in a fast paced manner and endlessly repeat this with the other side. Running this betgame is probably done best in a tomcat or any other java application server.
