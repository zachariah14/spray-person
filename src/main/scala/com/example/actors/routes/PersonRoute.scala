package com.example.actors.routes

import com.example.model.Person
import com.example.model.PersonJsonProtocol._
import com.example.services.{PersonServiceImpl, PersonService}
import akka.actor.Props
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport._
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport
import akka.actor.Actor
import org.slf4j.LoggerFactory

/**
* Factory method for Props configuration files for actors
*/
object PersonRoute {
  def props: Props = Props(new PersonRoute())
}

/**
 * Actor that handles requests that begin with "person"
 */
class PersonRoute() extends Actor with PersonRouteTrait {
  def actorRefFactory = context
  def receive = runRoute(personRoute)
}

/**
 * Separate routing logic in an HttpService trait so that the
 * routing logic can be tested outside of an actor system in specs/mockito tests
 */
trait PersonRouteTrait extends HttpService with SprayJsonSupport{

  private val personService = new PersonServiceImpl
  val log = LoggerFactory.getLogger(classOf[PersonRouteTrait])

  val personRoute = {
    get {
      pathEnd {
        complete {
          log.debug("Hitting Get All Persons")
          val persons = personService.getPersons
          persons match {
            case head :: tail => persons
            case Nil => StatusCodes.NoContent
          }
        }
      } ~
      path(LongNumber) { personId =>
        log.debug(s"Hitting Get Person by Id:${personId}")
        val person = personService.getPersonById(personId)
        complete(person)
      }
    } ~
    (post & pathEnd) {
      entity(as[Person]) { person =>
        log.debug("posting to create a person")
        val newPerson = personService.addPerson(person)
        complete(StatusCodes.Created, newPerson)
      }
    } ~
    (put & path(LongNumber) & pathEnd) { personId =>
      entity(as[Person]) { person =>
        log.debug(s"updating a person with the id: ${personId}")
        val updatedPerson = personService.updatePerson(person.copy(id = Some(personId)))
        updatedPerson match {
          case true => complete(StatusCodes.NoContent)
          case false => complete(StatusCodes.NotFound)
        }
      }
    } ~
    (delete & path(LongNumber) & pathEnd) { personId =>
       log.debug(s"deleting a person with the id: ${personId}")
      personService.deletePerson(personId)
      complete(StatusCodes.NoContent)
    }
  }

}
