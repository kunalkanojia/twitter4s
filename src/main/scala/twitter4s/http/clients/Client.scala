package twitter4s.http.clients

import scala.concurrent.Future

import spray.http.{HttpRequest, HttpResponse}
import spray.httpx.unmarshalling.{FromResponseUnmarshaller, UnmarshallerLifting}
import twitter4s.http.unmarshalling.JsonSupport
import twitter4s.providers.ActorRefFactoryProvider
import twitter4s.util.ActorContextExtractor

trait Client extends JsonSupport with ActorContextExtractor with UnmarshallerLifting {
  self: ActorRefFactoryProvider =>

  implicit class RichHttpRequest(val request: HttpRequest) {
    def respondAs[T: Manifest]: Future[T] = sendReceiveAs[T](request)
  }

  def sendReceiveAs[T: FromResponseUnmarshaller](request: HttpRequest) =
    pipeline apply request

  // TODO - logRequest, logResponse customisable?
  def pipeline[T: FromResponseUnmarshaller]: HttpRequest => Future[T]

  def sendReceive = spray.client.pipelining.sendReceive

  def logRequest: HttpRequest => HttpRequest = { request =>
    log.info(s"${request.method} ${request.uri}")
    log.debug(s"${request.method} ${request.uri} | ${request.entity.asString}")
    request
  }

  def logResponse(requestStartTime: Long)(implicit request: HttpRequest): HttpResponse => HttpResponse = { response =>
    val elapsed = System.currentTimeMillis - requestStartTime
    log.info(s"${request.method} ${request.uri} (${response.status}) | ${elapsed}ms")
    log.debug(s"${request.method} ${request.uri} (${response.status}) | ${response.entity.asString}")
    response
  }

}
