package de.betgame.sportdb



import grails.test.mixin.*
import spock.lang.*

@TestFor(GamesController)
@Mock(Games)
class GamesControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.gamesInstanceList
            model.gamesInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.gamesInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def games = new Games()
            games.validate()
            controller.save(games)

        then:"The create view is rendered again with the correct model"
            model.gamesInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            games = new Games(params)

            controller.save(games)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/games/show/1'
            controller.flash.message != null
            Games.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def games = new Games(params)
            controller.show(games)

        then:"A model is populated containing the domain instance"
            model.gamesInstance == games
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def games = new Games(params)
            controller.edit(games)

        then:"A model is populated containing the domain instance"
            model.gamesInstance == games
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/games/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def games = new Games()
            games.validate()
            controller.update(games)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.gamesInstance == games

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            games = new Games(params).save(flush: true)
            controller.update(games)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/games/show/$games.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/games/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def games = new Games(params).save(flush: true)

        then:"It exists"
            Games.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(games)

        then:"The instance is deleted"
            Games.count() == 0
            response.redirectedUrl == '/games/index'
            flash.message != null
    }
}
